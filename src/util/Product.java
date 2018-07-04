package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Product {
	private int id,  num,  comnum;
	private double score,price;
	private String name, path;
	private String owner;
	
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

	public void setPrice(int price) {
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

	public static void insertProduct(Product p){
		Connection conn = Database.getConnect();
		try{
			PreparedStatement pst = conn.prepareStatement("INSERT INTO `shixun`.`product` ( `price`, `num`, `name`, `owner`, `path`, `score`,comnum) " +
					"VALUES (?,?,?,?,?,?,?,?);");

				//pst.setInt(1,p.getId());
				pst.setDouble(1,p.getPrice());
				pst.setInt(2,p.getNum());
				pst.setString(3,p.getName());
				pst.setString(4,p.getOwner());
				pst.setString(5,p.getPath());
				pst.setDouble(6,p.getScore());
				pst.setInt(7,p.getComnum());
				pst.executeUpdate();

		}catch (SQLException e){
			e.printStackTrace();

		}


	}

}
