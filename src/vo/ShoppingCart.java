package vo;

import util.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/9 11:29
 */
public class ShoppingCart {
    private User user;
    private  Product product;
    int num;
    Date startTime;

    public User getUser(){return user;}

    public void setUser(String uid){
        Connection conn = Database.getConnect();
        String sql = "select * from users where id='"+uid+"';";

        try{
            Statement st=conn.createStatement();
            //System.out.println(sql);
            ResultSet rs =  st.executeQuery(sql);
            if(rs.next()){
                User u = new User(rs.getString("id"),rs.getString("pwd"),rs.getString("info"),
                        rs.getString("addr"),rs.getString("tel"),rs.getString("sex"),
                        rs.getString("rights"),rs.getDouble("money"));
                //return u;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //return null;
    }
}
