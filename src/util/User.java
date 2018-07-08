package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	private String id, pwd, info, add, tel, sex, right;
	private Double money;
	
	public User(String id, String pwd, String info, String add, String tel, String sex, String right, Double money) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.info = info;
		this.add = add;
		this.tel = tel;
		this.sex = sex;
		this.right = right;
		this.money = money;
	}
	
	public static User getUser(String id) {
		Connection conn = Database.getConnect();
		try{
			Statement st=conn.createStatement();
			ResultSet rs =  st.executeQuery("select * from users where id="+id);
			if(rs.next()){
				User u = new User(rs.getString("id"),rs.getString("pwd"),rs.getString("info"),
						rs.getString("add"),rs.getString("tel"),rs.getString("sex"),
						rs.getString("rights"),rs.getDouble("money"));
				return u;
			}
		}catch (SQLException e){
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

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
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

}
