package vo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Database;

public class Comment {
	private int id, product, score;
	private String content, purchaser;
	private Date comDate;

	public Comment(int id, int product, int score, String content, String purchaser, Date comDate) {
		super();
		this.id = id;
		this.product = product;
		this.score = score;
		this.content = content;
		this.purchaser = purchaser;
		this.comDate = comDate;
	}
	
	public Date getComDate() {
		return comDate;
	}

	public void setComDate(Date comDate) {
		this.comDate = comDate;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public static List<Comment> getCommentList(int pid) {
		Connection conn = Database.getConnect();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM comment WHERE product=" + pid + ";");
			List<Comment> result = new ArrayList<>();
			while (rs.next()) {
				Comment c = new Comment(rs.getInt("id"), pid, rs.getInt("score"), rs.getString("content"),
						rs.getString("purchaser"), rs.getDate("commentDate"));
				result.add(c);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void comment(double score, String content, int product, String purchaser, Date comDate, int pid) {
		String sql = "INSERT INTO comment (content, product, purchaser, score, commentDate) VALUES ('" + content + "', '"
				+ product + "', '" + purchaser + "', '" + score + "','" + comDate + "');";
		Database.update(sql);
		sql = "SELECT comnum, score FROM product WHERE id = '" + product + "';";
		int comnum = 0;
		double oldscore = 0;
		Connection connect = Database.getConnect();
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
			comnum = rs.getInt("comnum") + 1;
			oldscore = rs.getDouble("score");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		score = (oldscore * (comnum - 1) + score) / comnum;
		sql = "UPDATE product SET score='" + score + "', comnum='" + comnum + "' WHERE id='" + product + "';";
		Database.update(sql);
		sql = "UPDATE orders SET states='" + 5 + "' WHERE id=" + pid;
		Database.update(sql);
	}
}
