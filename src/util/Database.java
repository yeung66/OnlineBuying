package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;


/**
 * @ author: 杨浩麟
 * @ date: 2018/7/3 11:16
 */
public class Database {
    private static Connection connect=getConnect();



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
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int update(String sql) {
        try {
            Statement st = connect.createStatement();
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
