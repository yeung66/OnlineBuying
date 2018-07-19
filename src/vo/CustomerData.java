package vo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import util.Database;

public class CustomerData {

	private String type;
	private double money;
	private double totMoney;
	private String month;

	public CustomerData() {
		money = totMoney = 0;
		type = "0";
		month = "0";
	}

	public CustomerData(String type, double money, double totMoney, String month) {
		super();
		this.type = type;
		this.money = money;
		this.totMoney = totMoney;
		this.month = month;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public static List<CustomerData> getCustomerDataList(String uid) {
		CustomerData[] mlist = new CustomerData[15];
		String sql = "select month(starttime) as month,product.price,product.ptype,orders.quantity\r\n"
				+ "from orders inner join product on orders.product = product.id\r\n"
				+ "where month(starttime) > month(date_SUB(CURDATE(), INTERVAL 3 MONTH)) and orders.purchaser='" + uid
				+ "'\r\n" + "order by month(starttime),product.ptype";
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int month;
			if (!rs.next()) {
				return Arrays.asList(mlist);
			} else {
				month = rs.getInt("month");
			}
			rs.previous();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {
					mlist[i * 5 + j].setMonth(month + i + "");
					mlist[i * 5 + j].setType(j + "");
				}
			}
			while (rs.next()) {
				String mon = rs.getString("month");
				String ptype = rs.getString("ptype");
				double money = rs.getDouble("price") * rs.getInt("quantity")
						+ mlist[Integer.parseInt(mon) * 5 + Integer.parseInt(ptype)].getMoney();
				mlist[Integer.parseInt(mon) * 5 + Integer.parseInt(ptype)].setMoney(money);
			}
			for (int i = 0; i < 3; i++) {
				double tot = 0;
				for (int j = 0; j < 5; j++) {
					tot += mlist[i * 5 + j].getMoney();
				}
				for (int j = 0; j < 5; j++) {
					mlist[i * 5 + j].setTotMoney(tot);
				}
			}
			return Arrays.asList(mlist);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
