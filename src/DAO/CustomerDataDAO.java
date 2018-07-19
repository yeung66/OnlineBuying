package DAO;

import util.Database;
import vo.CustomerData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/18 18:40
 */
public class CustomerDataDAO {
    private static Connection conn=Database.getConnect();

    public static List<CustomerData> getCustomerDataList(String uid) {
        CustomerData[] mlist = new CustomerData[15];
        for(int i= 0; i < 15; i++){
            mlist[i] = new CustomerData();
        }
        String sql = "select month(starttime) as month,product.price,product.ptype,orders.quantity\r\n"
                + "from orders inner join product on orders.product = product.id\r\n"
                + "where month(starttime) > month(date_SUB(CURDATE(), INTERVAL 3 MONTH)) and orders.purchaser='" + uid
                + "'\r\n" + "order by month(starttime),product.ptype";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int month;
            if (!rs.next()) {
                return Arrays.asList(mlist);
            } else {
                month = rs.getInt("month");
            }
            rs.previous();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    mlist[i * 5 + j].setMonth(month + i + "");
                    mlist[i * 5 + j].setType(j + "");
                }
            }
            while (rs.next()) {
                String mon = rs.getString("month");
                String ptype = rs.getString("ptype");
                double money = rs.getDouble("price") * rs.getInt("quantity")
                        + mlist[(Integer.parseInt(mon)-month) * 5 + Integer.parseInt(ptype)].getMoney();
                mlist[(Integer.parseInt(mon)-month) * 5 + Integer.parseInt(ptype)].setMoney(money);
            }
            for (int i = 0; i < 3; i++) {
                double tot = 0;
                for (int j = 0; j < 5; j++) {
                    tot += mlist[i * 5 + j].getMoney();
                }
                for (int j = 0; j < 5; j++) {
                    mlist[i * 5 + j].setTotMoney(tot);
                }
            }
            return Arrays.asList(mlist);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
