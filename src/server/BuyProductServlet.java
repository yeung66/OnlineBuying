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
		if(request.getSession().getAttribute("uid") == null) {
			out.print("<script>");
			out.print("alert('请登录!');");
			out.print("window.location.href='login_registe.jsp'");
			out.print("</script>");
			out.close();
		}
		String type = (String) request.getSession().getAttribute("type");
        if(type.equals("1")) {
        	 out = response.getWriter();
             out.print("<script>");
             out.print("alert('商家无权限购买！');");
             out.print("window.history.go(-1)");
             out.print("</script>");
             out.close();
        }
		String purchaser = (String) request.getSession().getAttribute("uid");
		Double money=0.0;
		int product = Integer.parseInt(request.getParameter("pid"));
		if(purchaser.equals(Product.getProductInfo(product).getOwner())) {
			out.print("<script>");
			out.print("alert('不可购买自己发布的商品!');");
			out.print("window.history.go(-1)");
			out.print("</script>");
			out.close();
		}
		Double price = Product.getProductInfo(product).getPrice();
		int quantity = 1;
		if(request.getParameter("buyNumber") == null)
			quantity = 1;
		else if(Integer.parseInt(request.getParameter("buyNumber")) <= 0) {
			out.print("<script>");
			out.print("alert('购买数量非法!');");
			out.print("window.history.go(-1)");
			out.print("</script>");
			out.close();
			return;
		}
		else
			quantity = Integer.parseInt(request.getParameter("buyNumber"));
		if(quantity > Product.getProductInfo(product).getNum()){
			out.print("<script>");
    		out.print("alert('购买数量超出库存!');");
			out.print("window.history.go(-1)");
    		out.print("</script>");
    		out.close();
        	return;
		}
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
			out.print("window.history.go(-1)");
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
		sql = "UPDATE product SET num=" + (Product.getProductInfo(product).getNum() - quantity) + " WHERE id=" + product;
		Database.update(sql);
		out.print("<script>");
		out.print("alert('购买成功!');");
		out.print("window.location.href='jsp/alreadyBuy.jsp'");
		out.print("</script>");
		out.close();
	}

}
