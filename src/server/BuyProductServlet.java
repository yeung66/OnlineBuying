package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Database;
import vo.Product;

/**
 * Servlet implementation class BuyProductServlet
 */
@WebServlet(name = "BuyProductServlet" ,urlPatterns = "/BuyProductServlet")
public class BuyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter  out = response.getWriter();
		out.print("<meta   http-equiv='Content-Type'   content='text/html;   charset=UTF-8'>");   
		Double money=0.0;
		int product = Integer.parseInt(request.getParameter("pid"));
		Double price = Product.getProductInfo(product).getPrice();
		int quantity = Integer.parseInt(request.getParameter("buyNumber"));
		String purchaser = (String) request.getSession().getAttribute("uid");
		String sql = "SELECT money FROM users WHERE id = '" + purchaser + "';";
		Connection connect=Database.getConnect();
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
				money = rs.getDouble("money");
			}
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        if(money < price * quantity) {
    		out.print("<script>");
    		out.print("alert('购买失败!');");
    		out.print("</script>");
    		out.close();
        	return;
        }else {
        	money -= price * quantity;
        	sql = "UPDATE users SET money = '" + money + "' WHERE id = '" + purchaser + "';";
        	Database.update(sql);
        }
		String states = "0";
		Date starttime = new Date(System.currentTimeMillis());
		sql = "INSERT INTO orders (purchaser, product, states, quantity, starttime) VALUES ('" + purchaser + "','" + product + "','" 
				+ states + "','" + quantity + "','" + starttime + "');";
		Database.update(sql);
		sql = "UPDATE product SET num=" + (Product.getProductInfo(product).getNum() - 1) + " WHERE id=" + product;
		Database.update(sql);
		out.print("<script>");
		out.print("alert('购买成功!');");
		out.print("window.location.href='jsp/alreadyBuy.jsp'");
		out.print("</script>");
		out.close();
	}

}
