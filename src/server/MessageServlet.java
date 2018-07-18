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
        response.setContentType("text/text");
        String from = request.getParameter("from");
        if(type.equals("0")){
            List<Message> m = Message.getUncheckedMessage(from,uid);
            response.getWriter().write(JSON.toJSONString(m));
        }else if(type.equals("1")){
            int count = Message.setMessageChecked(from,uid);
            WebSocket.sendMes2Head(uid,count);
        }else if(type.equals("2")){
           response.getWriter().print(Message.getAllUncheckedMessageNum(uid));
        }else if(type.equals("3")){
            List<Message> m = Message.getHistoryMessage(uid,request.getParameter("to"));
            response.getWriter().write(JSON.toJSONString(m));
        }
    }
}
