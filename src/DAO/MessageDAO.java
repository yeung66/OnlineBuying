package DAO;

import javafx.util.Pair;
import util.Database;
import vo.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/19 9:16
 */
public class MessageDAO {
    private static Connection conn = Database.getConnect();

    public static void insertMessage(String from,String to,String content,int state){
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

    public static List<Message> getUncheckedMessage(String from, String to){
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

    public static List<Message> getHistoryMessage(String from,String to){
        List<Message> result = new ArrayList<>();
        try{
            PreparedStatement st = conn.prepareStatement("SELECT * FROM message where send=? and receive=? or send=? and receive=? ORDER BY time");
            st.setString(1,from);
            st.setString(2,to);
            st.setString(3,to);
            st.setString(4,from);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Message m = new Message();
                m.setSend(rs.getString("send"));
                m.setReceive(rs.getString("receive"));
                m.setContent(rs.getString("content"));
                m.setState(rs.getInt("state"));
                result.add(m);
            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
