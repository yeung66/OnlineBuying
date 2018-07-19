package server.bussiness;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDAO;


/**
 * Servlet implementation class AlterProductServlet
 */
@WebServlet("/AlterProductServlet")
public class AlterProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlterProductServlet() {
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
		PrintWriter  out = response.getWriter();
		out.print("<meta   http-equiv='Content-Type'   content='text/html;   charset=UTF-8'>");   
		int pid = Integer.parseInt(request.getParameter("pid"));
		String name = request.getParameter("name");
		String owner = (String) request.getSession().getAttribute("uid");
		double price = Double.valueOf(request.getParameter("price"));
		int num = Integer.parseInt(request.getParameter("num"));
		String info = request.getParameter("info");
		String type = request.getParameter("type");
		ProductDAO.alterProduct(pid, name, owner, price, num, info, type);
		out.print("<script>");
		out.print("alert('修改成功!');");
		out.print("window.location.href='jsp/table_list_img.jsp'");
		out.print("</script>");
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
