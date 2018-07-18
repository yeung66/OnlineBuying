package server.admin;

import vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/9 14:38
 */
@WebServlet(name = "AdminProductServlet",urlPatterns = "/adminProduct")
public class AdminProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("uid")!=null && ((String)session.getAttribute("type")).equals("2")){
            int pid = Integer.valueOf(request.getParameter("id"));
            String status=request.getParameter("status");
            if(Product.confirmProduct(pid,status))
                response.getWriter().write("success");
            return;
        }
        response.getWriter().write("fail");

    }
}
