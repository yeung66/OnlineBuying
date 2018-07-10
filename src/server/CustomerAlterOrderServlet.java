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
@WebServlet("/CustomerAlterOrderServlet")
public class CustomerAlterOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerAlterOrderServlet() {
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

		String states = request.getParameter("states");
		String operation = request.getParameter("operation");
		int id = Integer.parseInt(request.getParameter("id"));
		String sql;
		if (states.equals("0") && operation.equals("qv")) {
			sql = "DELETE FROM orders WHERE id= " + id + ";";
			Database.update(sql);
		} else if (states.equals("1") && operation.equals("tui")) {
			states = "2";
			sql = "UPDATE orders SET states = '" + states + "' WHERE id = " + id + ";";
			Database.update(sql);
		} else if (states.equals("1") && operation.equals("shou")) {
			states = "4";
			sql = "UPDATE orders SET states = '" + states + "' WHERE id = " + id + ";";
			Database.update(sql);
		} else if (states.equals("4") && operation.equals("ping")) {
			states = "5";
			sql = "UPDATE orders SET states = '" + states + "' WHERE id = " + id + ";";
			Database.update(sql);
			request.getRequestDispatcher("../jsp/comment.jsp").forward(request, response);
		}
		
	}

}
