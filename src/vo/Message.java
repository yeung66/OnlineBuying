package vo;

import com.alipay.api.domain.Data;
import javafx.util.Pair;
import util.Database;

import java.sql.*;
import java.sql.Date;
import java.util.*;


/**
 * @ author: 杨浩麟
 * @ date: 2018/7/10 11:30
 */
public class Message {
    private int id;
    private String send;
    private String receive;
    private String content;
    private int state;
    private Date time;

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public int getState() {
        return state;
    }

    public String getSend() {
        return send;
    }

    public String getReceive() {
        return receive;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSend(String from) {
        this.send = from;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setReceive(String to) {
        this.receive = to;
    }

    public static void insertMessage(String from,String to,String content,int state){
        Connection conn = Database.getConnect();
        try{
            PreparedStatement st = conn.prepareStatement("insert into message (send,receive,content,state) VALUES (?,?,?,?)");
            st.setString(1,from);
            st.setString(2,to);
            st.setString(3,content);
            st.setInt(4,state);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Message> getUncheckedMessage(String from,String to){
        Connection conn = Database.getConnect();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from message where receive='"+to+"' and send='"+from+"' and state='0' ORDER by time");
            List<Message> result=new ArrayList<>();
            while (rs.next()){
                Message m = new Message();
                m.setContent(rs.getString("content"));
                m.setSend(rs.getString("send"));
                m.setId(rs.getInt("id"));
                result.add(m);
            }

            return result;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static int getAllUncheckedMessageNum(String uid){
        Connection conn = Database.getConnect();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from message where receive='"+uid+"' and state='0'");
            if(rs.next()) return rs.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();

        }
        return 0;
    }

    public static List<Pair<String,String>> getRelatedUser(String id){
        Connection conn = Database.getConnect();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select DISTINCT send,state from message where receive='"+id+"' ORDER BY state");
            Map<String,String> temp = new HashMap<>();
            List<Pair<String,String>> result=new ArrayList<>();
            while (rs.next()){
                String send = rs.getString("send");
                String state = rs.getInt("state")==0?"0":"1";
                if(!temp.containsKey(send)) {
                    result.add(new Pair<>(send,state));
                    temp.put(send,state);
                }
            }
            rs = st.executeQuery("select DISTINCT receive from message where send='"+id+"'");
            while (rs.next()){
                String receive = rs.getString("receive");
                if(!temp.containsKey(receive)) result.add(new Pair<>(receive,"1"));
            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static int setMessageChecked(String from,String to){
        Connection conn = Database.getConnect();
        int count = 0;
        try{
            Statement countSt = conn.createStatement();
            ResultSet rs = countSt.executeQuery("select COUNT(*) from message where send='"+from+"' and receive='"+to+"' and state='0'");
            if(rs.next()) count=rs.getInt(1);
            PreparedStatement st = conn.prepareStatement("update message set state=1 where send=? and receive=?");
            st.setString(1,from);
            st.setString(2,to);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }
}
