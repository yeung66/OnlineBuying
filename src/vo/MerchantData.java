package vo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Database;

public class MerchantData {

	private String type;
	private int num;
	private double money;
	private double totMoney;
	private String month;

	public MerchantData(String type, int num, double money, double totMoney, String month) {
		super();
		this.type = type;
		this.num = num;
		this.money = money;
		this.totMoney = totMoney;
		this.month = month;
	}

	public MerchantData() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getTotMoney() {
		return totMoney;
	}

	public void setTotMoney(double totMoney) {
		this.totMoney = totMoney;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public static List<MerchantData> getMerchantDataList(String uid) {
		List<MerchantData> mlist = new ArrayList<MerchantData>();
		String sql = "select month(starttime) as month,product.price,product.ptype,orders.quantity\r\n"
				+ "from orders inner join product on orders.product = product.id\r\n"
				+ "where starttime > DATE_SUB(CURDATE(), INTERVAL 3 MONTH) and product.owner='" + uid + "'\r\n"
				+ "order by month(starttime),product.ptype";
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			double totMoney = 0;
			if (!rs.next()) {
				return mlist;
			}
			while (rs.next()) {
				MerchantData md = new MerchantData();
				md.setMonth(rs.getString("month"));
				md.setType(rs.getString("ptype"));
				int num = rs.getInt("quantity");
				double money = rs.getDouble("price") * rs.getInt("quantity");
				while (rs.next() && rs.getString("month").equals(md.getMonth())
						&& rs.getString("ptype").equals(md.getType())) {
					num += rs.getInt("quantity");
					money += rs.getDouble("price") * rs.getInt("quantity");
				}
				md.setMoney(money);
				md.setNum(num);
				mlist.add(md);
				rs.previous();
			}
			for (MerchantData m : mlist) {
				totMoney += m.getMoney();
			}
			for (MerchantData m : mlist) {
				m.setTotMoney(totMoney);
			}
			return mlist;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
