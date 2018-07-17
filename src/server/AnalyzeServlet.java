package server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import vo.CustomerData;
import vo.MerchantData;

/**
 * Servlet implementation class MerchantAnalyzeServlet
 */
@WebServlet("/AnalyzeServlet")
public class AnalyzeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnalyzeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid = (String) request.getSession().getAttribute("uid");
		String utype = (String) request.getSession().getAttribute("type");
		if (utype.equals("0")) {
			List<CustomerData> clist = CustomerData.getCustomerDataList(uid);
			String Jsonproduct = JSON.toJSONString(clist);
			request.getSession().setAttribute("analyzeCustomer", Jsonproduct);
			request.getRequestDispatcher("jsp/test.jsp").forward(request, response);
		} else {
			List<MerchantData> mlist = MerchantData.getMerchantDataList(uid);
			String Jsonproduct = JSON.toJSONString(mlist);
			request.getSession().setAttribute("analyzeMerchant", Jsonproduct);
			request.getRequestDispatcher("jsp/test.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}