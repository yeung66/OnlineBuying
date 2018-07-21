package server.bussiness;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.OrderDAO;
import server.util.Response;

/**
 * Servlet implementation class MerchantAlterOrder
 */
@WebServlet("/MerchantAlterOrder")
public class MerchantAlterOrder extends HttpServlet {

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
		int i = 1, id = -1;
		while(true) {
			if(request.getParameter("id-"+i) != null) {
				id = Integer.parseInt(request.getParameter("id-"+i));
				break;
			}
			i++;
		}
		if (!OrderDAO.merchantAlterOrder(operation, id)) {
			Response.replyAndGoBack("失败!",response);

		} else {
			Response.replyAndRedirect("成功!","jsp/ordersForShop.jsp",response);
		}
	}



}
