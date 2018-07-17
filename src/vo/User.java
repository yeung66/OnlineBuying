package vo;

import util.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		String sql = "select * from users where id='"+id+"';";
		Connection conn = Database.getConnect();
		try{
			Statement st=conn.createStatement();
			//System.out.println(sql);
			ResultSet rs =  st.executeQuery(sql);
			if(rs.next()){
				User u = new User(rs.getString("id"),rs.getString("pwd"),rs.getString("info"),
						rs.getString("addr"),rs.getString("tel"),rs.getString("sex"),
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

	public static String getRight(String id,String pwd){
		Connection conn = Database.getConnect();
		try{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select rights from users where id='"+id+"' and pwd='"+pwd+"'");
			if(rs.next()) return rs.getString("rights");
		}catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

}
