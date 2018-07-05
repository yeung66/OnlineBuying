//package server;
//
//import com.alibaba.fastjson.JSON;
//import util.Database;
//import util.Product;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.xml.crypto.Data;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.Statement;
//import java.util.List;
//
//
//@WebServlet(name = "SearchServlet",  urlPatterns = "/SearchServlet")
//public class SearchServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String search = request.getParameter("search");
//        List<Product> list= Database.searchProduct(search);
//        HttpSession session=request.getSession();
//      /*  if (list != null) {
//              session.setAttribute("search",list);
//              request.getRequestDispatcher("search.jsp").forward(request,response);
//        }
//        else {
//            session.setAttribute("search", "failed");
//        }*/
//        String Jsonproduct = JSON.toJSONString(list);
//        response.getWriter().print(Jsonproduct);
//    }
//
//
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request,response);
//    }
//}
