package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import segmenter.*;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/3 11:16
 */
public class Database {
    //private Connection connect;



    public static Connection getConnect() {
        Connection connect=null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/orcl");
            connect = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }

    public static boolean checkExist(String sql) {
        Connection connect=getConnect();
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @ author: 叶晟柯
     * @ date: 2018/7/3 16.24
     */
    public static List<Product> searchProduct(String s) {
        Connection connect=getConnect();
        segmenter segmt = new segmenter();
        List<String> nouns = segmt.seg(s);
        String sql = "select * from product where name like '";
        sql = sql + "%" + nouns.get(0) + "%'";
        for (int i = 1; i < nouns.size(); i++) {
            sql = sql + "or name like '%" + nouns.get(i) + "%'";
        }
        List<Product> list = new ArrayList<>();
        try {

            PreparedStatement stmt = connect.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product pro = new Product(Integer.getInteger(rs.getString(1)), Integer.getInteger(rs.getString(4)),
                        Integer.getInteger(rs.getString(6)), Integer.getInteger(rs.getString(7)),
                        Integer.getInteger(rs.getString(8)), rs.getString(2),
                        rs.getString(3), rs.getString(5));
                list.add(pro);
            }
        } catch (Exception e) {
        }
        return list;
    }

}