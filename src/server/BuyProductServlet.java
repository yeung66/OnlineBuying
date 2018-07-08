package server;

import java.io.IOException;
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
import util.Product;

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
		System.out.print("123");
		Double money=0.0;
		int product = Integer.parseInt(request.getParameter("pid"));
		Double price = Product.getProductInfo(product).getPrice();
		int quantity = Integer.parseInt(request.getParameter("buyNumber"));
		String purchaser = (String) request.getSession().getAttribute("uid");
		String sql = "SELECT money FROM users WHERE id = '" + purchaser + "';";
		Connection connect=Database.getConnect();
		System.out.print("234");
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
		System.out.print("345");
        if(money < price * quantity) {
			response.getWriter().print("failed");
        	return;
        }else {
        	money -= price * quantity;
        	sql = "UPDATE user SET money = " + money + "WHERE id = '" + purchaser + "';";
        	Database.update(sql);
        }
		System.out.print("456");
		String states = "new";
		Date starttime = new Date(System.currentTimeMillis());
		sql = "INSERT INTO orders (purchaser, product, states, quantity, starttime) VALUES ('" + purchaser + "','" + product + "','" 
				+ states + "','" + quantity + "','" + starttime + "');";
		Database.update(sql);
		response.getWriter().print("s");
	}

}
