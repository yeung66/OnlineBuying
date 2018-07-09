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
		double score = Double.parseDouble(request.getParameter("rating"));
		String content = request.getParameter("content");
		int product = Integer.parseInt(request.getParameter("pid"));
		String purchaser = request.getSession().getAttribute("uid").toString();
		Date comDate = new Date(System.currentTimeMillis());
		String sql = "INSERT INTO comment (content, product, purchaser, score, commentDate) VALUES ('" + content + "', '"
				+ product + "', '" + purchaser + "', '" + score + "','" + comDate + "');";
		Database.update(sql);
		sql = "SELECT comnum, score FROM product WHERE id = '" + product + "';";
		int comnum = 0;
		double oldscore = 0;
		Connection connect = Database.getConnect();
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
			comnum = rs.getInt("comnum") + 1;
			oldscore = rs.getDouble("score");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		score = (oldscore * (comnum - 1) + score) / comnum;
		sql = "UPDATE product SET score='" + score + "', comnum='" + comnum + "' WHERE id='" + product + "';";
		Database.update(sql);

	}

}
