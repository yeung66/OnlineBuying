package server.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDAO;
import server.util.Response;

/**
 * Servlet implementation class BuyProductServlet
 */
@WebServlet(name = "BuyProductServlet", urlPatterns = "/BuyProductServlet")
public class BuyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<meta   http-equiv='Content-Type'   content='text/html;   charset=UTF-8'>");
		if (request.getSession().getAttribute("uid") == null) {
			Response.replyAndRedirect("请登录!","login_registe.jsp",response);
			return;
		}
		String type = (String) request.getSession().getAttribute("type");
		if (type.equals("1")) {
			Response.replyAndGoBack("商家无权限购买！",response);
			return;
		}
		String purchaser = (String) request.getSession().getAttribute("uid");
		int product = Integer.parseInt(request.getParameter("pid"));
		if (purchaser.equals(ProductDAO.getProductInfo(product).getOwner())) {
			Response.replyAndGoBack("不可购买自己发布的商品!",response);
			return;
		}
		Double price = ProductDAO.getProductInfo(product).getPrice();
		int quantity = 1;
		if (request.getParameter("buyNumber") == null)
			quantity = 1;
		else if (Integer.parseInt(request.getParameter("buyNumber")) <= 0) {
			Response.replyAndGoBack("购买数量非法!",response);
			return;
		} else
			quantity = Integer.parseInt(request.getParameter("buyNumber"));
		if (quantity > ProductDAO.getProductInfo(product).getNum()) {
			Response.replyAndGoBack("购买数量超出库存!",response);
			return;
		}
		if (!ProductDAO.buyProduct(purchaser, price, quantity, product)) {
			Response.replyAndGoBack("购买失败!",response);
			return;
		} else {
			Response.replyAndRedirect("购买成功!","jsp/alreadyBuy.jsp",response);
		}
	}

}
