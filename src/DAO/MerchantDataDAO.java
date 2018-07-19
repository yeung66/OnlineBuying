package DAO;

import util.Database;
import vo.MerchantData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class MerchantDataDAO {
    private static Connection conn = Database.getConnect();

    public static List<MerchantData> getMerchantDataList(String uid) {
        MerchantData[] mlist = new MerchantData[15];
        String sql = "select month(starttime) as month,product.price,product.ptype,orders.quantity\r\n"
                + "from orders inner join product on orders.product = product.id\r\n"
                + "where month(starttime) > month(date_SUB(CURDATE(), INTERVAL 3 MONTH)) and product.owner='" + uid
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
                int num = rs.getInt("quantity") + mlist[Integer.parseInt(mon) * 5 + Integer.parseInt(ptype)].getNum();
                double money = rs.getDouble("price") * rs.getInt("quantity")
                        + mlist[Integer.parseInt(mon) * 5 + Integer.parseInt(ptype)].getMoney();
                mlist[Integer.parseInt(mon) * 5 + Integer.parseInt(ptype)].setMoney(money);
                mlist[Integer.parseInt(mon) * 5 + Integer.parseInt(ptype)].setNum(num);
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
