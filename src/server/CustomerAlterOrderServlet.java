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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter  out = response.getWriter();
		out.print("<meta   http-equiv='Content-Type'   content='text/html;   charset=UTF-8'>");   
		String operation = request.getParameter("operation");
		int i = Integer.parseInt(request.getParameter("i"));
		int id = Integer.parseInt(request.getParameter("id-"+i));
		String sql;
		if (operation.equals("qv")) {
			sql = "DELETE FROM orders WHERE id= " + id + ";";
			Database.update(sql);
		} else if (operation.equals("tui")) {
			sql = "UPDATE orders SET states = '" + 2 + "' WHERE id = " + id + ";";
			Database.update(sql);
		} else if (operation.equals("shou")) {
			sql = "UPDATE orders SET states = '" + 4 + "' WHERE id = " + id + ";";
			Database.update(sql);
		} else {
			out.print("<script>");
    		out.print("alert('失败!');");
    		out.print("</script>");
    		out.close();
        	return;
		}
		out.print("<script>");
		out.print("alert('成功!');");
		out.print("window.location.href='jsp/alreadyBuy.jsp'");
		out.print("</script>");
		out.close();
	}

}
