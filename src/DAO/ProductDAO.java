package DAO;

import segmenter.segmenter;
import util.Database;
import vo.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            pst.setString(9, p.getpType());
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
                p.setStatus(rs.getString("status"));
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
                p.setOwner(rs.getString("owner"));
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
                + ",information='" + info + "', ptype='" + type + "' WHERE id=" + pid + ";";
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

    public static List<Product> searchProduct(String s) {
        Connection connect=conn;

        String reg = "[^\u4e00-\u9fa5]";
        String Chinese = s.replaceAll(reg, "");
        Pattern pat = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher mat = pat.matcher(s);
        String NotChinese =   mat.replaceAll("");

        List<Product> list = new ArrayList<>();
        if (!Chinese.equals("")) {
            segmenter segmt = new segmenter();
            List<String> nouns = segmt.seg(Chinese);

            if ((nouns!=null)&&(!nouns.isEmpty())) {
                String sql = "select * from product where name like '";
                sql = sql + "%" + nouns.get(0) + "%'";
                for (int i = 1; i < nouns.size(); i++) {
                    sql = sql + "or name like '%" + nouns.get(i) + "%'";
                }
                // System.out.print(sql);

                try {

                    PreparedStatement stmt = connect.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        //  System.out.print(rs.getString(3));
                        Product pro = new Product(rs.getInt(1), rs.getDouble(4),
                                rs.getInt(6), rs.getDouble(7),
                                rs.getInt(8), rs.getString(2),
                                rs.getString(3), rs.getString(5),rs.getString(9),
                                rs.getString(11));
                        pro.setStatus(rs.getString("status"));
                        if (pro.getStatus().equals("pass")){
                            //Jsonproduct = JSON.toJSONString(pro);
                            // System.out.print(Jsonproduct);
                            list.add(pro);}
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }}else{
                String sql = "select * from product where name like '%"+Chinese+"%'";
                try {

                    PreparedStatement stmt = connect.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        //  System.out.print(rs.getString(3));
                        Product pro = new Product(rs.getInt(1), rs.getDouble(4),
                                rs.getInt(6), rs.getDouble(7),
                                rs.getInt(8), rs.getString(2),
                                rs.getString(3), rs.getString(5),rs.getString(9),
                                rs.getString(11));
                        pro.setStatus(rs.getString("status"));
                        //Jsonproduct = JSON.toJSONString(pro);
                        // System.out.print(Jsonproduct);
                        if (pro.getStatus().equals("pass")){
                            //Jsonproduct = JSON.toJSONString(pro);
                            // System.out.print(Jsonproduct);
                            list.add(pro);}
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!NotChinese.equals("")) {
                segmenter se = new segmenter();
                String[] st = se.engSeg(NotChinese);
                String sql="select * from product where name like '%"+st[0]+"%'";

                for (int i=1;i<st.length;i++) {
                    sql = sql+"or name like '%"+st[i]+"%'";
                }
                try {

                    PreparedStatement stmt = connect.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        //  System.out.print(rs.getString(3));
                        Product pro = new Product(rs.getInt(1), rs.getDouble(4),
                                rs.getInt(6), rs.getDouble(7),
                                rs.getInt(8), rs.getString(2),
                                rs.getString(3), rs.getString(5),rs.getString(9),
                                rs.getString(11));
                        pro.setStatus(rs.getString("status"));
                        if (pro.getStatus().equals("pass")){
                            //Jsonproduct = JSON.toJSONString(pro);
                            // System.out.print(Jsonproduct);
                            list.add(pro);}
                    }
                } catch (Exception e) {
                }
            }

        }else {
            if (!NotChinese.equals("")) {
                segmenter se = new segmenter();
                String[] st = se.engSeg(NotChinese);
                String sql="select * from product where name like '%"+st[0]+"%'";

                for (int i=1;i<st.length;i++) {
                    sql = sql+"or name like '%"+st[i]+"%'";
                }
                try {


                    PreparedStatement stmt = connect.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        //  System.out.print(rs.getString(3));
                        Product pro = new Product(rs.getInt(1), rs.getDouble(4),
                                rs.getInt(6), rs.getDouble(7),
                                rs.getInt(8), rs.getString(2),
                                rs.getString(3), rs.getString(5),rs.getString(9),
                                rs.getString(11));
                        pro.setStatus(rs.getString("status"));
                        if (pro.getStatus().equals("pass")){
                            //Jsonproduct = JSON.toJSONString(pro);
                            // System.out.print(Jsonproduct);
                            list.add(pro);}
                    }
                } catch (Exception e) {
                }

            }
        }
        return list;
    }

}
