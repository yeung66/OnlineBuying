package vo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Database;

public class CustomerData {

	private String type;
	private double money;
	private double totMoney;
	private String month;

	public CustomerData() {

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
		List<CustomerData> clist = new ArrayList<CustomerData>();
		String sql = "select month(starttime) as month,product.price,product.ptype,orders.quantity\r\n"
				+ "from orders inner join product on orders.product = product.id\r\n"
				+ "where starttime > DATE_SUB(CURDATE(), INTERVAL 3 MONTH) and orders.purchaser='" + uid + "'\r\n"
				+ "order by month(starttime),product.ptype";
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			double totMoney = 0;
			if (!rs.next()) {
				return clist;
			}
			while (rs.next()) {
				CustomerData cd = new CustomerData();
				String ptype = rs.getString("ptype");
				if(ptype.equals("0"))
					cd.setType("文具卡片");
				else if(ptype.equals("1"))
					cd.setType("特色美食");
				else if(ptype.equals("2"))
					cd.setType("服饰箱包");
				else if(ptype.equals("3"))
					cd.setType("居家生活");
				else if(ptype.equals("4"))
					cd.setType("数码电器");
				// int num = rs.getInt("quantity");
				double money = rs.getDouble("price") * rs.getInt("quantity");
				while (rs.next() && rs.getString("month").equals(cd.getMonth())
						&& rs.getString("ptype").equals(cd.getType())) {
					// num += rs.getInt("quantity");
					money += rs.getDouble("price") * rs.getInt("quantity");
				}
				cd.setMoney(money);
				clist.add(cd);
				rs.previous();
			}
			for (CustomerData c : clist) {
				totMoney += c.getMoney();
			}
			for (CustomerData c : clist) {
				c.setTotMoney(totMoney);
			}
			return clist;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
