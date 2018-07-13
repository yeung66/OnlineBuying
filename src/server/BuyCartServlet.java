package server;

import vo.Product;
import vo.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BuyCartServlet" , urlPatterns = "/BuyCartServlet")
public class BuyCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String uid = (String)request.getSession().getAttribute("uid");
        String[] pid =request.getParameterValues("checkItem");
        PrintWriter out = response.getWriter();
        if (pid!=null) {
            if (ShoppingCart.buyProduct(uid, pid)) {
                out.print("<script>");
                out.print("alert('购买成功');");
                out.print("window.location.href='jsp/alreadyBuy.jsp'");
                out.print("</script>");
                out.close();
            } else {
                out.print("<script>");
                out.print("alert('购买失败')");
                out.print("window.location.href='jsp/shoppingCart.jsp'");
                out.print("</script>");
                out.close();
            }
        }
        else {
            out.print("<script>");
            out.print("alert('操作不能为空！')");
            out.print("window.location.href='jsp/shoppingCart.jsp'");
            out.print("</script>");
            out.close();
        }
    }
}
