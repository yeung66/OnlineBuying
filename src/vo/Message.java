package vo;

import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
            for(Message m:result){
                st.executeUpdate("update message set state='1' where id="+m.getId());
            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getRelatedUser(String id){
        Connection conn = Database.getConnect();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select send from message where receive='"+id+"' ORDER BY state");
            List<String> result=new ArrayList<>();
            while (rs.next()){
                if(!result.contains(rs.getString("send"))) result.add(rs.getString("send"));
            }
            rs = st.executeQuery("select receive from message where send='"+id+"'");
            while (rs.next()){
                if(!result.contains(rs.getString("receive"))) result.add(rs.getString("receive"));
            }

            return result;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
