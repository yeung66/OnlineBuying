package server.customer;


import DAO.ShoppingCartDAO;
import server.util.Response;

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
            Response.replyAndGoBack("商家无权限购买！",response);
            return;
        }
        String[] pid =request.getParameterValues("checkItem");
        PrintWriter out = response.getWriter();
        String mes,url;
        if (pid!=null) {
            if (ShoppingCartDAO.buyProduct(uid, pid)) {
                mes="购买成功";
                url="jsp/alreadyBuy.jsp";

            } else {
                mes="购买失败";
                url="jsp/shoppingCart.jsp";
            }
        }
        else {
            mes="操作不能为空！";
            url="jsp/shoppingCart.jsp";

        }
        Response.replyAndRedirect(mes,url,response);
    }
}
