package vo;

import java.sql.Date;


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
}
