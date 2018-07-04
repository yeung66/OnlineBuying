package server;

import util.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
* MADE BY:叶晟柯
 */
@WebServlet(name = "ChangeUserServlet",urlPatterns = "/ChangeUserServlet")
public class ChangeUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String pwd = request.getParameter("password");
           String info = request.getParameter("information");
           String tel = request.getParameter("telephone");
           String add = request.getParameter("address");
           String sex = request.getParameter("sex");
           String id = request.getParameter("id");//传递用户名给后台数据库处理
           int i=Database.update("update user set pwd =\""+pwd+"\" "+"where id = \""+id+"\"");
               i=Database.update("update user set info =\""+info+"\" "+"where id = \""+id+"\"");
               i=Database.update("update user set tel =\""+tel+"\" "+"where id = \""+id+"\"");
               i=Database.update("update user set add =\""+add+"\" "+"where id = \""+id+"\"");
               i=Database.update("update user set sex =\""+sex+"\" "+"where id = \""+id+"\"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
