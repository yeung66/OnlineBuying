package server;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class CommentServlet
 */
@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int score = Integer.parseInt(request.getParameter("rating"));
		String content = request.getParameter("content");
		int product = Integer.parseInt(request.getParameter("pid"));
		String purchaser = request.getSession().getAttribute("uid").toString();
		String sql = "INSERT INTO comment (content, product, purchaser, score) VALUES ('" + content + "', '"
				+ product + "', '" + purchaser + "', '" + score + "');";
		Database.update(sql);
		sql = "SELECT comnum, score FROM product WHERE id = '" + product + "';";
		int comnum, oldscore;
		Connection connect = Database.getConnect();
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			comnum = rs.getInt("comnum") + 1;
			oldscore = rs.getInt("oldscore");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		score = (oldscore * (comnum - 1) + score) / comnum;
		sql = "UPDATE product SET score=" + comnum + "', comnum='" + oldscore + "'WHERE id='" + product + "';";
		Database.update(sql);
	}

}
