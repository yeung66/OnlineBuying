package server;

import com.alibaba.fastjson.JSON;
import vo.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/11 15:31
 */
@WebServlet(name = "MessageServlet",urlPatterns = "/message")
public class MessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type= request.getParameter("type");
        String uid = (String)request.getSession().getAttribute("uid");
        response.setCharacterEncoding("utf-8");
        if(type.equals("0")){
            String from = request.getParameter("from");
            List<Message> m = Message.getUncheckedMessage(from,uid);
            response.getWriter().write(JSON.toJSONString(m));
        }
    }
}
