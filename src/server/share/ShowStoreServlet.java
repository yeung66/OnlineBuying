package server.share;

import DAO.ProductDAO;
import DAO.UserDAO;
import com.alibaba.fastjson.JSON;
import vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowStoreServlet" , urlPatterns = "/ShowStoreServlet")
public class ShowStoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          int pid = Integer.parseInt(request.getParameter("pid"));
          String sid = ProductDAO.getProductInfo(pid).getOwner();
        List<Product> list = ProductDAO.getProductList(sid);
        String str = JSON.toJSONString(list);
        request.getSession().setAttribute("storeGoood",str);
        request.getSession().setAttribute("sid",sid);
        request.getSession().setAttribute("stel", UserDAO.getUser(ProductDAO.getProductInfo(pid).getOwner()).getTel());
        request.getRequestDispatcher("jsp/storeShow.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
