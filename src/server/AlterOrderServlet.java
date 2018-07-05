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

/**
 * Servlet implementation class AlterOrderServlet
 */
@WebServlet("/AlterOrderServlet")
public class AlterOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterOrderServlet() {
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
		int money;
		int product = (int) request.getSession().getAttribute("product");
		int quantity = (int) request.getSession().getAttribute("quantity");
		int id = (int) request.getSession().getAttribute("id");
		String purchaser = (String) request.getSession().getAttribute("purchaser");
		String sql = "SELECT money FROM users WHERE id = '" + purchaser + "';";
		Connection connect=Database.getConnect();
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            money  = rs.getInt("money");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        if(money > product * quantity) {
        	return;
        }else {
        	money -= product * quantity;
        	sql = "UPDATE user SET money = " + money + "WHERE id = '" + purchaser + "';";
        	Database.update(sql);
        }
		String states = (String) request.getSession().getAttribute("states");
		Date starttime = new Date(System.currentTimeMillis());
		sql = "UPDATE orders SET purchaser = '"  + purchaser + "', states = '"
				+ states + "', quantuty = '" + quantity + "', starttime = '" + starttime + "' WHERE id = "+ id +";";
		Database.update(sql);
	}

}
