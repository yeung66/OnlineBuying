package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.text.Segment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
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

    public static int update(String sql) {
        Connection connect=getConnect();
        try {
            Statement st = connect.createStatement();
            int rs = st.executeUpdate(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    /**
     * @ author: 叶晟柯
     * @ date: 2018/7/3 16.24
     */
    public static List<Product> searchProduct(String s) {
        Connection connect=getConnect();

        String reg = "[^\u4e00-\u9fa5]";
        String Chinese = s.replaceAll(reg, "");
        Pattern pat = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher mat = pat.matcher(s);
        String NotChinese =   mat.replaceAll("");

        List<Product> list = new ArrayList<>();
        if (!Chinese.equals("")) {
            segmenter segmt = new segmenter();
            List<String> nouns = segmt.seg(s);

            if ((nouns!=null)&&(!list.isEmpty())) {
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
                                rs.getString(3), rs.getString(5));
                        //Jsonproduct = JSON.toJSONString(pro);
                        // System.out.print(Jsonproduct);
                        list.add(pro);
                    }
                } catch (Exception e) {
                }}
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
                                    rs.getString(3), rs.getString(5));
                            //Jsonproduct = JSON.toJSONString(pro);
                            // System.out.print(Jsonproduct);
                            list.add(pro);
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
                                rs.getString(3), rs.getString(5));
                        //Jsonproduct = JSON.toJSONString(pro);
                        // System.out.print(Jsonproduct);
                        list.add(pro);
                    }
                } catch (Exception e) {
                }

            }
        }
        return list;
    }

}
