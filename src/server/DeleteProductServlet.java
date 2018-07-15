package server;

import vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteProductServlet" , urlPatterns = "/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pID = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if ( Product.deleteProduct(pID)){
            out.print("<script>");
            out.print("alert('删除成功');");
            out.print("window.location.href='jsp/table_list_img.jsp'");
            out.print("</script>");
            out.close();
        }
        else {   out.print("<script>");
            out.print("alert('删除成功');");
            out.print("window.location.href='jsp/table_list_img.jsp'");
            out.print("</script>");
            out.close();}
    }
}
