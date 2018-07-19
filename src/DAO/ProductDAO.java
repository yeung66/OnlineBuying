package DAO;

import util.Database;
import vo.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/19 9:36
 */
public class ProductDAO {
    private static Connection conn = Database.getConnect();

    public static void insertProduct(Product p) {
        try {
            PreparedStatement pst = conn.prepareStatement(
                    "INSERT INTO product ( price, num, name, owner, path, score,comnum,information,ptype) "
                            + "VALUES (?,?,?,?,?,?,?,?,?);");

            // pst.setInt(1,p.getId());
            pst.setDouble(1, p.getPrice());
            pst.setInt(2, p.getNum());
            pst.setString(3, p.getName());
            pst.setString(4, p.getOwner());
            pst.setString(5, p.getPath());
            pst.setDouble(6, p.getScore());
            pst.setInt(7, p.getComnum());
            pst.setString(8, p.getInformation());
            pst.setString(9, p.getType());
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static List<Product> getAllGoodList() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from product where status='pass';");
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setPrice(rs.getDouble("price"));
                p.setName(rs.getString("name"));
                p.setPath(rs.getString("path"));
                p.setType(rs.getString("ptype"));
                p.setScore(rs.getDouble("score"));
                p.setInformation(rs.getString("information"));
                result.add(p);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Product> getProductList(String uid) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st
                    .executeQuery("SELECT id,price,name,path,score,num,ptype,status from product where owner='" + uid + "'");
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setPrice(rs.getDouble("price"));
                p.setName(rs.getString("name"));
                p.setPath(rs.getString("path"));
                p.setNum(rs.getInt("num"));
                p.setScore(rs.getDouble("score"));
                p.setType(rs.getString("ptype"));
                p.setStatus("status");
                result.add(p);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Product getProductInfo(int productID) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from product where id=" + productID);
            if (rs.next()) {
                Product p = new Product(rs.getInt("id"), rs.getDouble("price"), rs.getInt("num"), rs.getDouble("score"),
                        rs.getInt("comnum"), rs.getString("name"), rs.getString("owner"), rs.getString("path"),
                        rs.getString("information"), rs.getString("ptype"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteProduct(String pID) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("delete from product where id = " + pID);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public static List<Product> getNotExamineProducts() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from product where status='0'");
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setPrice(rs.getDouble("price"));
                p.setName(rs.getString("name"));
                p.setPath(rs.getString("path"));
                p.setNum(rs.getInt("num"));
                p.setScore(rs.getDouble("score"));
                p.setType(rs.getString("ptype"));
                result.add(p);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean confirmProduct(int id, String status) {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("update product set status='" + status + "' where id='" + id + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int alterProduct(int pid, String name, String owner, double price, int num, String info,
                                   String type) {
        String sql = "UPDATE product SET name='" + name + "',owner='" + owner + "',price=" + price + ",num=" + num
                + ",information='" + info + "' ptype='" + type + "' WHERE id=" + pid + ";";
        return Database.update(sql);
    }

    public static boolean buyProduct(String purchaser, double price, int quantity, int product) {
        Double money = 0.0;
        String sql = "SELECT money FROM users WHERE id = '" + purchaser + "';";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                money = rs.getDouble("money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        if (money < price * quantity) {
            return false;
        } else {
            money -= price * quantity;
            sql = "UPDATE users SET money = '" + money + "' WHERE id = '" + purchaser + "';";
            Database.update(sql);
        }
        String states = "0";
        Date starttime = new Date(System.currentTimeMillis());
        sql = "INSERT INTO orders (purchaser, product, states, quantity, starttime) VALUES ('" + purchaser + "','"
                + product + "','" + states + "','" + quantity + "','" + starttime + "');";
        Database.update(sql);
        sql = "UPDATE product SET num=" + (ProductDAO.getProductInfo(product).getNum() - quantity) + " WHERE id="
                + product;
        Database.update(sql);
        return true;
    }

    public static Product setProduct(String pid) {
        Product product =null;
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
        return product;
    }
}
