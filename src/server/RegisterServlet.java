package server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Database;

/**
 * Servlet implementation class SignUp
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String id, pwd, info = "", add, tel, sex, right;

		id = request.getParameter("id");
		pwd = request.getParameter("password");
		add = request.getParameter("add");
		tel = request.getParameter("tel");
		sex = request.getParameter("sex");
		right = request.getParameter("right");

		String sql = "SELECT * FROM `shixun`.`users` WHERE `id` = '" + id+"'";
		if (Database.checkExist(sql)) {
			response.getWriter().print("fail");
			return;
		}
		sql = "INSERT INTO `shixun`.`users` (`id`, `pwd`, `tel`, `add`, `sex`, `rights`) VALUES ('" + id + "','"
				+ pwd + "'" + ",'" + tel + "','" + add + "'," + sex + "," + right + "" + ");";
		if(Database.update(sql) > 0)
			response.getWriter().print("success");
		else
			response.getWriter().print("fail");
	}

}