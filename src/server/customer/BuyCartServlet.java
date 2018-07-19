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

@WebServlet(name = "BuyCartServlet" , urlPatterns = "/BuyCartServlet")
public class BuyCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String uid = (String)request.getSession().getAttribute("uid");
        String type = (String) request.getSession().getAttribute("type");
        if(type.equals("1")) {
        	 PrintWriter out = response.getWriter();
             out.print("<script>");
             out.print("alert('商家无权限购买！');");
             out.print("window.history.go(-1)");
             out.print("</script>");
             out.close();
        }
        String[] pid =request.getParameterValues("checkItem");
        PrintWriter out = response.getWriter();
        if (pid!=null) {
            if (ShoppingCartDAO.buyProduct(uid, pid)) {
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
