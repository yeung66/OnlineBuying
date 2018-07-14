package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;

import util.*;

/**
 * Servlet implementation class WithDraw
 */
@WebServlet("/WithDraw")
public class WithDraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithDraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter  out = response.getWriter();
		out.print("<meta   http-equiv='Content-Type'   content='text/html;   charset=UTF-8'>");
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", "GBK", AlipayConfig.alipay_public_key, "RSA2");
		AlipayFundTransToaccountTransferRequest alireq = new AlipayFundTransToaccountTransferRequest();
		String out_biz_no = request.getParameter("WIDout_trade_no");
		String payee_account = request.getParameter("WIDaccount");
		Double amount = Double.valueOf(request.getParameter("WIDtotal_amount"));
		String payee_real_name = request.getParameter("WIDreal_name");
		String uid = (String)request.getSession().getAttribute("uid");
		String sql = "SELECT money FROM users WHERE id = '" + uid + "';";
		Double money = 0.0;
		Connection connect=Database.getConnect();
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				money = rs.getDouble("money");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		if(money < amount){
			out.print("<script>");
			out.print("alert('账户余额不足!');");
			out.print("</script>");
			out.close();
			return;
		}
		alireq.setBizContent("{" + "\"out_biz_no\":\""+out_biz_no+"\"," + "\"payee_type\":\"ALIPAY_LOGONID\","
				+ "\"payee_account\":\""+payee_account+"\"," + "\"amount\":\""+amount+"\"," + "\"payer_show_name\":\"珞珈商城\","
				+ "\"payee_real_name\":\""+payee_real_name+"\"," + "\"remark\":\"提现\"" + "}");
		AlipayFundTransToaccountTransferResponse alires;
		try {
			alires = alipayClient.execute(alireq);
			if (alires.isSuccess()) {
		        money -= amount;
				sql = "UPDATE users SET money=" + money + " WHERE id='" + uid + "';";
				Database.update(sql);
				out.print("<script>");
	    		out.print("alert('提现成功!');");
	    		out.print("window.location.href='jsp/perInfo.jsp'");
	    		out.print("</script>");
	    		out.close();
			} else {
				out.print("<script>");
	    		out.print("alert('提现失败!');");
	    		out.print("window.location.href='jsp/perInfo.jsp'");
	    		out.print("</script>");
	    		out.close();
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
