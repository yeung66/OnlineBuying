package vo;

import java.sql.Date;


/**
 * @ author: 杨浩麟
 * @ date: 2018/7/10 11:30
 */
public class Message {
    private int id;
    private String from;
    private String to;
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

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public void setTo(String to) {
        this.to = to;
    }
}
