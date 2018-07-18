package DAO;

import util.Database;
import vo.CustomerData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/18 18:40
 */
public class CustomerDataDAO {
    private static Connection conn=Database.getConnect();

    public static List<CustomerData> getCustomerDataList(String uid) {
        List<CustomerData> clist = new ArrayList<CustomerData>();
        String sql = "select month(starttime) as month,product.price,product.ptype,orders.quantity\r\n"
                + "from orders inner join product on orders.product = product.id\r\n"
                + "where starttime > DATE_SUB(CURDATE(), INTERVAL 3 MONTH) and orders.purchaser='" + uid + "'\r\n"
                + "order by month(starttime),product.ptype";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) {
                return clist;
            }
            while (rs.next()) {
                CustomerData cd = new CustomerData();
                cd.setMonth(rs.getString("month"));
                String ptype = rs.getString("ptype");
                if (ptype.equals("0"))
                    cd.setType("文具卡片");
                else if (ptype.equals("1"))
                    cd.setType("特色美食");
                else if (ptype.equals("2"))
                    cd.setType("服饰箱包");
                else if (ptype.equals("3"))
                    cd.setType("居家生活");
                else if (ptype.equals("4"))
                    cd.setType("数码电器");
                // int num = rs.getInt("quantity");
                double money = rs.getDouble("price") * rs.getInt("quantity");
                while (rs.next() && rs.getString("month").equals(cd.getMonth())
                        && rs.getString("ptype").equals(cd.getType())) {
                    // num += rs.getInt("quantity");
                    money += rs.getDouble("price") * rs.getInt("quantity");
                }
                cd.setMoney(money);
                clist.add(cd);
                rs.previous();
            }
            double[] totMoney = { 0, 0, 0 };
            String month = clist.get(0).getMonth();
            int i = 0;
            for (CustomerData c : clist) {
                if (c.getMonth().equals(month))
                    totMoney[i] += c.getMoney();
                else {
                    i++;
                    month = c.getMonth();
                    totMoney[i] += c.getMoney();
                }
            }
            month = clist.get(0).getMonth();
            i = 0;
            for (CustomerData c : clist) {
                if (c.getMonth().equals(month))
                    c.setTotMoney(totMoney[i]);
                else {
                    i++;
                    month = c.getMonth();
                    c.setTotMoney(totMoney[i]);
                }
            }
            return clist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
