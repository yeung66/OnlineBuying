package server;

import com.alibaba.fastjson.JSON;
import util.Database;
import vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/*
    MADE BY :叶晟柯
 */
@WebServlet(name = "SearchServlet",  urlPatterns = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  System.out.println("1");

        String search = request.getParameter("search");
        String str = new String(search.getBytes("ISO-8859-1"),"UTF-8");
     // System.out.print(str);
    //  segmenter se = new segmenter();
      //List<String> list_=se.seg(str);

            List<Product> list = Database.searchProduct(str);
        HttpSession session=request.getSession();


      /*  if (list != null) {
              session.setAttribute("search",list);
              request.getRequestDispatcher("search.jsp").forward(request,response);
        }
        else {
            session.setAttribute("search", "failed");
        }*/
        String Jsonproduct = JSON.toJSONString(list);
    //  System.out.print(Jsonproduct);
    //  Jsonproduct =JSON.toJSONString(list_);
     //   System.out.print(Jsonproduct);
    //   response.getWriter().print(Jsonproduct);
    //    str = new String(search.getBytes("UTF-8"),"ISO-8859-1");
        session.setAttribute("search",Jsonproduct);

        request.getRequestDispatcher("jsp/searchRslt.jsp").forward(request,response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
