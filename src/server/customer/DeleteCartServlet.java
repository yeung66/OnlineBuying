package server.customer;

import DAO.ShoppingCartDAO;
import server.util.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCartServlet" , urlPatterns = "/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String uid = (String)request.getSession().getAttribute("uid");
        String pid =request.getParameter("gid");
        if (ShoppingCartDAO.deleteCart(uid,pid)) {
            Response.replyAndRedirect("删除成功","jsp/shoppingCart.jsp",response);
        } else {
            Response.replyAndRedirect("删除失败","jsp/shoppingCart.jsp",response);
        }
    }
}
