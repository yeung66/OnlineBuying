package vo;

import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private int id, product, quantity;
	private String purchaser, states;
	private Date startTime;

	public Order() {
	}

	public Order(int id, int product, int quantity, String purchaser, String states, Date startTime) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.purchaser = purchaser;
		this.states = states;
		this.startTime = startTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduct() {
		return product;
	}

	public void setProduct(int product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public static List<Order> getOrderList(String buyerID) {
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from orders where purchaser=" + buyerID);
			List<Order> result = new ArrayList<>();
			while (rs.next()) {

				Order o = new Order();
				o.setId(rs.getInt("id"));
				o.setProduct(rs.getInt("product"));
				o.setQuantity(rs.getInt("quantity"));
				o.setPurchaser(rs.getString("purchaser"));
				o.setStates(rs.getString("states"));
				o.setStartTime(rs.getDate("startTime"));
				result.add(o);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Order getOrderDetail(int pid, String purchaser) {
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			String sql = "select * from orders where product=" + pid + " and purchaser='" + purchaser + "';";
			ResultSet rs = st.executeQuery(sql);
			Order o = null;
			if (rs.next()) {
				o = new Order();
				o.setId(rs.getInt("id"));
				o.setQuantity(rs.getInt("quantity"));
			}
			return o;
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;
	}

	public static List<Order> getShopOrderList(String shopID) {
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			List<Order> result = new ArrayList<>();
			ResultSet rs = st.executeQuery(
					"SELECT orders.id, purchaser, product, states, quantity, starttime FROM orders,product where orders.product=product.id and product.owner = "
							+ shopID + ";");
			while (rs.next()) {

				Order o = new Order();
				o.setId(rs.getInt("id"));
				o.setProduct(rs.getInt("product"));
				o.setQuantity(rs.getInt("quantity"));
				o.setPurchaser(rs.getString("purchaser"));
				o.setStates(rs.getString("states"));
				o.setStartTime(rs.getDate("startTime"));
				result.add(o);

			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
