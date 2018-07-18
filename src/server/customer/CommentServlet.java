package server.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;

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
		int pid = Integer.parseInt(request.getParameter("oid"));
		CommentDAO.comment(score, content, product, purchaser, comDate, pid);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('评论成功');");
		out.print("window.location.href='jsp/alreadyBuy.jsp'");
		out.print("</script>");
		out.close();
	}

}
