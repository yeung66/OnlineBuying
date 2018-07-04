package server;

import util.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/4 10:10
 */
@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String owner = (String)request.getSession().getAttribute("uid");
        double price = (double)request.getSession().getAttribute("price");
        String path = request.getParameter("path");
        String num = request.getParameter("num");
        //Product.insertProduct(new Product());
    }
}
