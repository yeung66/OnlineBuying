package vo;

import util.AlipayConfig;
import util.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;

public class User {
	private String id, pwd, info, addr, tel, sex, right;
	private Double money;

	public User(String id, String pwd, String info, String addr, String tel, String sex, String right, Double money) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.info = info;
		this.addr = addr;
		this.tel = tel;
		this.sex = sex;
		this.right = right;
		this.money = money;
	}

	public static User getUser(String id) {
		String sql = "select * from users where id='" + id + "';";
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			// System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				User u = new User(rs.getString("id"), rs.getString("pwd"), rs.getString("info"), rs.getString("addr"),
						rs.getString("tel"), rs.getString("sex"), rs.getString("rights"), rs.getDouble("money"));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public static String getRight(String id, String pwd) {
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select rights from users where id='" + id + "' and pwd='" + pwd + "'");
			if (rs.next())
				return rs.getString("rights");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void changeUser(String pwd, String info, String tel, String add, String sex, String id) {
		int i = Database.update("update users set pwd =\"" + pwd + "\" " + "where id = \"" + id + "\"");
		i = Database.update("update users set info =\"" + info + "\" " + "where id = \"" + id + "\"");
		i = Database.update("update users set tel =\"" + tel + "\" " + "where id = \"" + id + "\"");
		i = Database.update("update users set addr =\"" + add + "\" " + "where id = \"" + id + "\"");
		i = Database.update("update users set sex =\"" + sex + "\" " + "where id = \"" + id + "\"");
	}

	public static boolean register(String id, String pwd, String info, String add, String tel, String right,
			String sex) {
		String sql = "SELECT * FROM `shixun`.`users` WHERE `id` = '" + id + "'";
		if (Database.checkExist(sql)) {
			return false;
		}
		sql = "INSERT INTO `shixun`.`users` (`id`, `pwd`, `tel`, `addr`, `sex`, `rights`) VALUES ('" + id + "','" + pwd
				+ "'" + ",'" + tel + "','" + add + "'," + sex + "," + right + "" + ");";
		if (Database.update(sql) > 0)
			return true;
		else
			return false;
	}

	public static int withdraw(String uid, String out_biz_no, String payee_account, Double amount,
			String payee_real_name) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", "GBK", AlipayConfig.alipay_public_key, "RSA2");
		AlipayFundTransToaccountTransferRequest alireq = new AlipayFundTransToaccountTransferRequest();

		String sql = "SELECT money FROM users WHERE id = '" + uid + "';";
		Double money = 0.0;
		Connection connect = Database.getConnect();
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				money = rs.getDouble("money");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		if (money < amount) {
			return 1;
		}
		alireq.setBizContent("{" + "\"out_biz_no\":\"" + out_biz_no + "\"," + "\"payee_type\":\"ALIPAY_LOGONID\","
				+ "\"payee_account\":\"" + payee_account + "\"," + "\"amount\":\"" + amount + "\","
				+ "\"payer_show_name\":\"珞珈商城\"," + "\"payee_real_name\":\"" + payee_real_name + "\","
				+ "\"remark\":\"提现\"" + "}");
		AlipayFundTransToaccountTransferResponse alires;
		try {
			alires = alipayClient.execute(alireq);
			if (alires.isSuccess()) {
				money -= amount;
				sql = "UPDATE users SET money=" + money + " WHERE id='" + uid + "';";
				Database.update(sql);
				return 2;
			} else {
				return 0;
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
