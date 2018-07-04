package server;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import util.User;

/**
 * Servlet implementation class SignUp
 */
	@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String id, pwd, info = "", add, tel, sex, right;
		
		id = request.getParameter("uid");
		pwd = request.getParameter("password");
		add = request.getParameter("add");
		tel = request.getParameter("tel");
		sex = request.getParameter("sex");
		right = request.getParameter("status");
		
		Connection con;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://120.78.150.152:3306/shixun";
		String user = "yeung";
		String password = "123456";
		PreparedStatement stmt;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM `shixun`.`users` WHERE `id` = " + id;
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "User name already exist! Please choose another name.");
				return;
			}
			sql = "INSERT INTO `shixun`.`users` (`id`, `pwd`, `info`, `tel`, `add`, `sex`, `rights`) VALUES (?,?,?,?,?,?,?);";
			stmt = (PreparedStatement) con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, pwd);
			stmt.setString(3, info);
			stmt.setString(4, tel);
			stmt.setString(5, add);
			stmt.setString(6, sex);
			stmt.setString(7, right);
			stmt.executeUpdate();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
