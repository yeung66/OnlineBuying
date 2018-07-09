package util;

import util.Database;
import vo.Product;
import vo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/9 11:29
 */
public class ShoppingCart {
    private User user;
    private Product product;
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

    public static boolean AddinSQL(ShoppingCart cart){
        String sql = "INSERT INTO `shixun`.`shoppingcart` (`uid`, `pid`, `num`, `starttime`) VALUES ('"+cart.user.getId()+"' , '"+cart.product.getId()+"' ,'"
                +cart.num+"' ,'"+cart.startTime+"')";
        if (Database.update(sql)!=-1){
            return true;
        }else{
            return false;
        }
    }

    public static List<ShoppingCart> searchFromSQL(String uid) {
        Connection conn = Database.getConnect();
        List<ShoppingCart> list = new ArrayList<>();
        String sql = "select * from shoppingcart where uid = '"+uid+"'";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs =statement.executeQuery();
            while (rs.next()){
                ShoppingCart cart = new ShoppingCart(rs.getString(1),rs.getString(2),
                        rs.getInt(3),rs.getDate(4));
                list.add(cart);
            }
        }
        catch (Exception e){e.printStackTrace();}
        return list;
    }
    public static boolean deleteCart(String uid,String pid){
        Connection conn = Database.getConnect();
        try{
            PreparedStatement pstmt = conn.prepareStatement("delete from shoppingcart where uid = "+uid+" and pid = "+pid);
            pstmt.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();

        }
        return false;
          }
}
