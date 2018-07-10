package server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Database;

/**
 * Servlet implementation class MerchantAlterOrder
 */
@WebServlet("/MerchantAlterOrder")
public class MerchantAlterOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MerchantAlterOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String states = request.getParameter("states");
		String operation = request.getParameter("operation");
		int id = Integer.parseInt(request.getParameter("id"));
		String sql;
		if (states.equals("0") && operation.equals("qv")) {
			sql = "DELETE FROM orders WHERE id= " + id + ";";
			Database.update(sql);
		} else if (states.equals("0") && operation.equals("fa")) {
			states = "1";
			sql = "UPDATE orders SET states = '" + states + "' WHERE id = " + id + ";";
			Database.update(sql);
		} else if (states.equals("2") && operation.equals("yi")) {
			states = "3";
			sql = "UPDATE orders SET states = '" + states + "' WHERE id = " + id + ";";
			Database.update(sql);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
