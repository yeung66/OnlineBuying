package DAO;

import util.Database;
import vo.ShoppingCart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDAO {
    private static Connection conn = Database.getConnect();

    public static boolean AddinSQL(ShoppingCart cart) {
        String sql = "SELECT num FROM shoppingcart where uid = '" + cart.getUser().getId() + "' and pid =" + cart.getProduct().getId();
        if (Database.checkExist(sql)) {

            Connection connect = Database.getConnect();
            int num = 0;
            try {
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    num = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            if (num + cart.getNum() > ProductDAO.getProductInfo(cart.getProduct().getId()).getNum()) {
                return false;
            } else {
                sql = "update shoppingcart set num =" + Integer.toString(num + cart.getNum()) + " where uid = '" + cart.getUser().getId() + "' and pid =" + cart.getProduct().getId();
                if (Database.update(sql) != -1) {
                    return true;
                } else {
                    return false;
                }
            }
        }else {
            if (cart.getNum()>ProductDAO.getProductInfo(cart.getProduct().getId()).getNum()){
                return false;
            }else {
                sql = "INSERT INTO `shixun`.`shoppingcart` (`uid`, `pid`, `num`, `starttime`) VALUES ('" + cart.getUser().getId() + "' , '" + cart.getProduct().getId() + "' ,'"
                        + cart.getNum() + "' ,'" + cart.getStartTime() + "')";
                if (Database.update(sql) != -1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public static List<ShoppingCart> searchFromSQL(String uid) {
        List<ShoppingCart> list = new ArrayList<>();
        String sql = "select * from shoppingcart where uid = '" + uid + "'";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ShoppingCart cart = new ShoppingCart(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getDate(4));
                list.add(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean deleteCart(String uid, String pid) {
        Connection conn = Database.getConnect();
        try {
            PreparedStatement pstmt = conn.prepareStatement("delete from shoppingcart where uid = " + uid + " and pid = " + pid);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public static ShoppingCart selectCart(String uid, String pid) {
        Connection conn = Database.getConnect();
        String sql = "select * from shoppingcart where uid = '" + uid + "'" + "and pid = " + pid;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ShoppingCart cart = new ShoppingCart(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getDate(4));
                return cart;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean buyProduct(String uid, String[] pid) {
        double money = 0.0;
        double total = 0.0;
        String sql = "SELECT money FROM users WHERE id = '" + uid + "';";
        Connection connect = Database.getConnect();
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                money = rs.getDouble("money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        for (int i = 0; i < pid.length; i++) {
            ShoppingCart cart = ShoppingCartDAO.selectCart(uid, pid[i]);
            total += cart.getNum() * cart.getProduct().getPrice();
        }
        if (money < total) {
            return false;
        } else {
            for (int i = 0; i < pid.length; i++) {
                ShoppingCart cart = ShoppingCartDAO.selectCart(uid, pid[i]);
                if (cart.getNum()> ProductDAO.getProductInfo(cart.getProduct().getId()).getNum()){
                    return false;
                }
            }
            money -= total;
            sql = "UPDATE users SET money = '" + money + "' WHERE id = '" + uid + "';";
            Database.update(sql);
            for (int i = 0; i < pid.length; i++) {
                ShoppingCart cart = ShoppingCartDAO.selectCart(uid, pid[i]);
                total += cart.getNum() * cart.getProduct().getPrice();
                String states = "0";
                java.util.Date starttime = new java.sql.Date(System.currentTimeMillis());
                sql = "INSERT INTO orders (purchaser, product, states, quantity, starttime) VALUES ('" + uid + "','" + pid[i] + "','"
                        + states + "','" + cart.getNum() + "','" + starttime + "');";
                Database.update(sql);
                sql = "UPDATE product SET num=" + (ProductDAO.getProductInfo(Integer.parseInt(pid[i])).getNum() - cart.getNum()) + " WHERE id=" + pid[i];
                Database.update(sql);
                ShoppingCartDAO.deleteCart(uid, pid[i]);
            }
        }
        return true;
    }
}
