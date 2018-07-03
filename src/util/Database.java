package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/3 11:16
 */
public class Database {
    private Connection connect;

    public Database(){
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/orcl");
            connect=ds.getConnection();
        }catch (NamingException|SQLException e){
            e.printStackTrace();
        }

    }

    public Connection getConnect() {
        return connect;
    }

    public boolean checkExist(String sql){
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
