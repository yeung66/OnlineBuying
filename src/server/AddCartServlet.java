package server;

import com.alibaba.fastjson.JSON;
import vo.Comment;
import vo.Product;
import vo.ShoppingCart;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "AddCartServlet" , urlPatterns = "/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String Pid = request.getParameter("pid");
        String Uid = (String)request.getSession().getAttribute("uid");
        User user = User.getUser(Uid);
        String s =request.getParameter("buyNumber");
        int num = Integer.parseInt(s);
        Date starttime = new Date(System.currentTimeMillis());
        ShoppingCart cart = new ShoppingCart(Uid,Pid,num,starttime);
       if (ShoppingCart.AddinSQL(cart)) {
           PrintWriter out = response.getWriter();
           out.print("<script>");
           out.print("alert('添加成功');");
           out.print("window.location.href='jsp/shoppingCart.jsp'");
           out.print("</script>");
           out.close();
       }else{ PrintWriter out = response.getWriter();
           out.print("<script>");
           out.print("alert('失败');");
           out.print("window.location.href='index.jsp'");
           out.print("</script>");
           out.close();}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
