package vo;

import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product {

	private int id, num, comnum;
	private double score, price;
	private String name, path;
	private String owner, information;
	private String status, type;
	
	public Product(int id, double price, int num, double score, int comnum, String name, String owner, String path,
			String information, String type) {
		super();
		this.id = id;
		this.num = num;
		this.comnum = comnum;
		this.score = score;
		this.price = price;
		this.name = name;
		this.path = path;
		this.owner = owner;
		this.information = information;
		this.type = type;
	}

	public Product() {
	}

	public Product(int id, double price, int num, double score, int comnum, String name, String owner, String path,
			String information) {
		super();
		this.id = id;
		this.price = price;
		this.num = num;
		this.score = score;
		this.comnum = comnum;
		this.name = name;
		this.owner = owner;
		this.path = path;
		this.information = information;
	}

	public Product(int id, double price, int num, double score, int comnum, String name, String owner, String path) {
		super();
		this.id = id;
		this.price = price;
		this.num = num;
		this.score = score;
		this.comnum = comnum;
		this.name = name;
		this.owner = owner;
		this.path = path;
		this.information = "";
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getComnum() {
		return comnum;
	}

	public void setComnum(int comnum) {
		this.comnum = comnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public static void insertProduct(Product p) {
		Connection conn = Database.getConnect();
		try {
			PreparedStatement pst = conn.prepareStatement(
					"INSERT INTO product ( price, num, name, owner, path, score,comnum,information,ptype) "
							+ "VALUES (?,?,?,?,?,?,?,?,?);");

			// pst.setInt(1,p.getId());
			pst.setDouble(1, p.getPrice());
			pst.setInt(2, p.getNum());
			pst.setString(3, p.getName());
			pst.setString(4, p.getOwner());
			pst.setString(5, p.getPath());
			pst.setDouble(6, p.getScore());
			pst.setInt(7, p.getComnum());
			pst.setString(8, p.getInformation());
			pst.setString(9, p.getType());
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public static List<Product> getAllGoodList() {
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from product where status='pass';");
			List<Product> result = new ArrayList<>();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setPrice(rs.getDouble("price"));
				p.setName(rs.getString("name"));
				p.setPath(rs.getString("path"));
				p.setType(rs.getString("ptype"));
				result.add(p);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Product> getProductList(String uid) {
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT id,price,name,path,score,num from product where owner='"+uid+"'");

			List<Product> result = new ArrayList<>();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setPrice(rs.getDouble("price"));
				p.setName(rs.getString("name"));
				p.setPath(rs.getString("path"));
				p.setNum(rs.getInt("num"));
				p.setScore(rs.getDouble("score"));
				p.setType(rs.getString("ptype"));
				result.add(p);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Product getProductInfo(int productID) {
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from product where id=" + productID);
			if (rs.next()) {
				Product p = new Product(rs.getInt("id"), rs.getDouble("price"), rs.getInt("num"), rs.getDouble("score"),
						rs.getInt("comnum"), rs.getString("name"), rs.getString("owner"), rs.getString("path"),
						rs.getString("information"),rs.getString("ptype"));
				return p;
			}
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean deleteProduct(String pID) {
		Connection conn = Database.getConnect();
		try {
			PreparedStatement pstmt = conn.prepareStatement("delete from product where id = " + pID);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
	}

	public static List<Product> getNotExamineProducts(){
		Connection conn = Database.getConnect();
		try{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from product where status='0'");
			List<Product> result = new ArrayList<>();
			while(rs.next()){
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setPrice(rs.getDouble("price"));
				p.setName(rs.getString("name"));
				p.setPath(rs.getString("path"));
				p.setNum(rs.getInt("num"));
				p.setScore(rs.getDouble("score"));
				p.setType(rs.getString("ptype"));
				result.add(p);
			}
			return result;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}

	}

	public static boolean confirmProduct(int id,String status) {
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			st.executeUpdate("update product set status='" + status + "' where id='" + id + "'");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static int alterProduct(int pid, String name, String owner, double price, int num,
			String info, String type) {
		String sql = "UPDATE product SET name='" + name + "',owner='" + owner + "',price=" + price + 
				",num=" + num + ",information='" + info + "' ptype='" + type + "' WHERE id=" + pid + ";";
		return Database.update(sql);
	}

}
