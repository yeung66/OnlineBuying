package server;

import util.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/3 14:52
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("id");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        String sql = "select * from users where id="+username+" and pwd="+password;
        if(Database.checkExist(sql)){
            response.getWriter().print("success");
        }else {
            response.getWriter().print("fail");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doPost(request,response);
    }
}
