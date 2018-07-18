package DAO;

import util.Database;
import vo.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/18 18:33
 */
public class CommentDAO {
    private static Connection conn = Database.getConnect();

    public static List<Comment> getCommentList(int pid) {
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
