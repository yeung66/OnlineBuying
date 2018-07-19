package DAO;

import util.Database;
import vo.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/19 9:21
 */
public class OrderDAO {

    private static Connection conn = Database.getConnect();

    public static List<Order> getOrderList(String buyerID) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from orders where purchaser='" + buyerID+"'");
            List<Order> result = new ArrayList<>();
            while (rs.next()) {

                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setProduct(rs.getInt("product"));
                o.setQuantity(rs.getInt("quantity"));
                o.setPurchaser(rs.getString("purchaser"));
                o.setStates(rs.getString("states"));
                o.setStartTime(rs.getDate("startTime"));
                result.add(o);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Order getOrderDetail(int pid, String purchaser) {
        try {
            Statement st = conn.createStatement();
            String sql = "select * from orders where product=" + pid + " and purchaser='" + purchaser + "';";
            ResultSet rs = st.executeQuery(sql);
            Order o = null;
            if (rs.next()) {
                o = new Order();
                o.setId(rs.getInt("id"));
                o.setQuantity(rs.getInt("quantity"));
            }
            return o;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static List<Order> getShopOrderList(String shopID) {
        try {
            Statement st = conn.createStatement();
            List<Order> result = new ArrayList<>();
            ResultSet rs = st.executeQuery(
                    "SELECT orders.id, purchaser, product, states, quantity, starttime FROM orders,product where orders.product=product.id and product.owner = "
                            + shopID + ";");
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setProduct(rs.getInt("product"));
                o.setQuantity(rs.getInt("quantity"));
                o.setPurchaser(rs.getString("purchaser"));
                o.setStates(rs.getString("states"));
                o.setStartTime(rs.getDate("startTime"));
                result.add(o);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static boolean customerAlterOrder(String operation, int id) {
        String sql;
        if (operation.equals("qv")) {
            sql = "DELETE FROM orders WHERE id= " + id + ";";
            Database.update(sql);
            return true;
        } else if (operation.equals("tui")) {
            sql = "UPDATE orders SET states = '" + 2 + "' WHERE id = " + id + ";";
            Database.update(sql);
            return true;
        } else if (operation.equals("shou")) {
            sql = "UPDATE orders SET states = '" + 4 + "' WHERE id = " + id + ";";
            Database.update(sql);
            return true;
        } else {
            return false;
        }
    }

    public static boolean merchantAlterOrder(String operation, int id) {
        String sql;
        if (operation.equals("qv")) {
            sql = "DELETE FROM orders WHERE id= " + id + ";";
            Database.update(sql);
            return true;
        } else if (operation.equals("fa")) {
            sql = "UPDATE orders SET states = '" + 1 + "' WHERE id = " + id + ";";
            Database.update(sql);
            return true;
        } else if (operation.equals("yi")) {
            sql = "UPDATE orders SET states = '" + 3 + "' WHERE id = " + id + ";";
            Database.update(sql);
            return true;
        } else {
            return false;
        }
    }
}
