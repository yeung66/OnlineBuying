package server.share;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;

import server.util.Response;
import util.*;
import vo.*;

/**
 * Servlet implementation class WithDraw
 */
@WebServlet("/WithDraw")
public class WithDraw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WithDraw() {
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
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<meta   http-equiv='Content-Type'   content='text/html;   charset=UTF-8'>");
		String out_biz_no = request.getParameter("WIDout_trade_no");
		String payee_account = request.getParameter("WIDaccount");
		Double amount = Double.valueOf(request.getParameter("WIDtotal_amount"));
		String payee_real_name = request.getParameter("WIDreal_name");
		String uid = (String) request.getSession().getAttribute("uid");
		int i = UserDAO.withdraw(uid, out_biz_no, payee_account, amount, payee_real_name);
		if (i == 1) {
			out.print("<script>");
			out.print("alert('账户余额不足!');");
			out.print("</script>");
			out.close();
		} else if (i == 2) {
			Response.replyAndRedirect("提现成功!","jsp/perInfo.jsp",response);
		} else {
			Response.replyAndRedirect("提现失败!","jsp/perInfo.jsp",response);
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
