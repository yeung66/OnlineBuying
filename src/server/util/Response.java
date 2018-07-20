package server.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Response {
    public static void replyAndRedirect(String mes, String url, HttpServletResponse response)throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<body><script>alert('"+mes+"')");
        response.getWriter().println("window.location.href='"+url+"'");
        response.getWriter().println("</script></body>");
    }

    public static void replyAndGoBack(String mes,HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<body><script>alert('"+mes+"')");
        response.getWriter().println("window.history.go(-1)");
        response.getWriter().println("</script></body>");
    }

    public static void replyAndClose(String mes,HttpServletResponse response)throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<body><script>alert('"+mes+"')");
        response.getWriter().println("window.close()");
        response.getWriter().println("</script></body>");
    }
}
