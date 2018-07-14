package server;

import java.io.IOException;
import java.io.PrintWriter;

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
		} else if (operation.equals("fa")) {
			sql = "UPDATE orders SET states = '" + 1 + "' WHERE id = " + id + ";";
			Database.update(sql);
		} else if (operation.equals("yi")) {
			sql = "UPDATE orders SET states = '" + 3 + "' WHERE id = " + id + ";";
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
		out.print("window.location.href='jsp/ordersForShop.jsp'");
		out.print("</script>");
		out.close();
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
