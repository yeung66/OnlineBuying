package server.bussiness;

import DAO.ProductDAO;
import server.util.Response;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProductServlet" , urlPatterns = "/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pID = request.getParameter("id");
        if ( ProductDAO.deleteProduct(pID)){
            Response.replyAndRedirect("删除成功","jsp/table_list_img.jsp",response);
        }
        else {
            Response.replyAndRedirect("删除失败","jsp/table_list_img.jsp",response);
        }

    }
}
