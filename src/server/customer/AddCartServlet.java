package server.customer;

import DAO.ShoppingCartDAO;
import DAO.UserDAO;
import server.util.Response;
import vo.ShoppingCart;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "AddCartServlet" , urlPatterns = "/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String Pid = request.getParameter("pid");
        String Uid = (String) request.getSession().getAttribute("uid");
        if (Uid == null){
            Response.replyAndRedirect("请先登录","login_registe.jsp",response);
            return;
        }
        String type = (String) request.getSession().getAttribute("type");
        if(type.equals("1")) {
            Response.replyAndGoBack("商家无权限购买！",response);
            return;
        }

        User user = UserDAO.getUser(Uid);
        String s = request.getParameter("buyNumber");
        int num=0;
        if (s == null){num = 1;}else{ num= Integer.parseInt(s);}
        if (num<= 0) {
            Response.replyAndGoBack("添加失败，购买数小于为0！",response);
            return;
        }
        else {
            Date starttime = new Date(System.currentTimeMillis());
            ShoppingCart cart = new ShoppingCart(Uid, Pid, num, starttime);
            if (ShoppingCartDAO.AddinSQL(cart)) {
                Response.replyAndRedirect("添加成功","jsp/shoppingCart.jsp",response);
                return;
            } else {
                Response.replyAndGoBack("失败",response);
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
