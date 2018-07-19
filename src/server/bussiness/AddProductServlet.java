package server.bussiness;

import DAO.ProductDAO;
import server.util.Response;
import vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/4 10:10
 */
@WebServlet("/addProduct")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String owner = (String)request.getSession().getAttribute("uid");
        double price = Double.valueOf(request.getParameter("price"));
        String info = request.getParameter("info");
        String type = request.getParameter("type");
        String num = request.getParameter("num");
        Part p =request.getPart("path");
        String header = p.getHeader("content-disposition");
        String path = header.substring(header.indexOf("filename=") + 10, header.length() - 1);

        String picName = UUID.randomUUID().toString().replace("-", "").toLowerCase()+getRealName(path);
        String realPath = this.getServletContext().getRealPath("images/upload/");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        InputStream is = p.getInputStream();
        FileOutputStream fos = new FileOutputStream(new File(file,picName));
        int len = -1;
        byte[] bytes = new byte[1024];
        while ((len = is.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fos.close();
        is.close();
        p.delete();
        ProductDAO.insertProduct(new Product(0,price,Integer.valueOf(num),0,0,name,owner,"images/upload/"+picName, info,type));
        Response.replyAndRedirect("添加商品成功","jsp/table_list_img.jsp",response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static String getRealName(String path) {
        int index = path.lastIndexOf("\\");

        if (index == -1) {
            index = path.lastIndexOf("/");
        }

        return path.substring(index + 1);
    }

}
