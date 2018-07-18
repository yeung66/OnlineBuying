package server.share;

import util.Database;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
* MADE BY:叶晟柯
 */
@WebServlet(name = "ChangeUserServlet", urlPatterns = "/ChangeUserServlet")
public class ChangeUserServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pwd = request.getParameter("pwd");
		String info = request.getParameter("info");
		String tel = request.getParameter("tel");
		String add = request.getParameter("add");
		String sex = request.getParameter("sex");
		String id = (String) request.getSession().getAttribute("uid");
		User.changeUser(pwd, info, tel, add, sex, id);
		response.getWriter().write("success");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
}
