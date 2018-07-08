package server;

import util.Database;

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
		int i = Database.update("update users set pwd =\"" + pwd + "\" " + "where id = \"" + id + "\"");
		i = Database.update("update users set info =\"" + info + "\" " + "where id = \"" + id + "\"");
		i = Database.update("update users set tel =\"" + tel + "\" " + "where id = \"" + id + "\"");
		i = Database.update("update users set add =\"" + add + "\" " + "where id = \"" + id + "\"");
		i = Database.update("update users set sex =\"" + sex + "\" " + "where id = \"" + id + "\"");
		response.getWriter().write("success");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
}
