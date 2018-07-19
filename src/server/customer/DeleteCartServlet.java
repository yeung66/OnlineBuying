package server.customer;

import DAO.ShoppingCartDAO;
import vo.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteCartServlet" , urlPatterns = "/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String uid = (String)request.getSession().getAttribute("uid");
        String pid =request.getParameter("gid");
        PrintWriter out = response.getWriter();
        if (ShoppingCartDAO.deleteCart(uid,pid)) {
            out.print("<script>");
            out.print("alert('删除成功');");
            out.print("window.location.href='jsp/shoppingCart.jsp'");
            out.print("</script>");
            out.close();
        } else {
            out.print("<script>");
            out.print("alert('删除失败')");
            out.print("window.location.href='jsp/shoppingCart.jsp'");
            out.print("</script>");
            out.close();
        }
    }
}
