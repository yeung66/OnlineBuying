package server.customer;

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
        String Uid = (String) request.getSession().getAttribute("uid");
        if (Uid == null){
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('请先登录');");
            out.print("window.location.href='login_registe.jsp'");
            out.print("</script>");
            out.close();
        }
        String type = (String) request.getSession().getAttribute("type");
        if(type.equals("1")) {
        	 PrintWriter out = response.getWriter();
             out.print("<script>");
             out.print("alert('商家无权限购买！');");
             out.print("window.history.go(-1)");
             out.print("</script>");
             out.close();
        }
        User user = User.getUser(Uid);
        String s = request.getParameter("buyNumber");
        int num=0;
        if (s == null){num = 1;}else{ num= Integer.parseInt(s);}
        if (num<= 0) {PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('添加失败，购买数小于为0！！');");
            out.print("window.location.href='index.jsp'");
            out.print("</script>");
            out.close();
        }
        else {
            Date starttime = new Date(System.currentTimeMillis());
            ShoppingCart cart = new ShoppingCart(Uid, Pid, num, starttime);
            if (ShoppingCart.AddinSQL(cart)) {
                PrintWriter out = response.getWriter();
                out.print("<script>");
                out.print("alert('添加成功');");
                out.print("window.location.href='jsp/shoppingCart.jsp'");
                out.print("</script>");
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                out.print("<script>");
                out.print("alert('失败');");
                out.print("window.location.href='index.jsp'");
                out.print("</script>");
                out.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
