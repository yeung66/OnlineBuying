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
    private int num;
    private Date startTime;

    public ShoppingCart(String uid,String pid,int num,Date startTime){
        setUser(uid);
        setProduct(pid);
        this.num=num;
        this.startTime=startTime;
    }

    public User getUser(){return user;}

    public void setUser(String uid){
        Connection conn = Database.getConnect();
        String sql = "select * from users where id='"+uid+"';";
        try{
            Statement st=conn.createStatement();
            //System.out.println(sql);
            ResultSet rs =  st.executeQuery(sql);
            if(rs.next()){
                user = new User(rs.getString("id"),rs.getString("pwd"),rs.getString("info"),
                        rs.getString("addr"),rs.getString("tel"),rs.getString("sex"),
                        rs.getString("rights"),rs.getDouble("money"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(String pid){
        Connection conn = Database.getConnect();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from product where id=" + pid);
            if (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getDouble("price"), rs.getInt("num"), rs.getDouble("score"),
                        rs.getInt("comnum"), rs.getString("name"), rs.getString("owner"), rs.getString("path"),
                        rs.getString("information"));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
