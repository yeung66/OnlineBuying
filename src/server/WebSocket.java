package server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/10 10:04
 */
@ServerEndpoint("/websocket/{id}")
public class WebSocket {
    private static ConcurrentHashMap<String,WebSocket> websocketMap = new ConcurrentHashMap<>();

    private Session session;
    private String uid;

    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session){
        this.session = session;
        this.uid = id;
        websocketMap.put(uid,this);
        System.out.println("新连接建立");
    }

    @OnClose
    public void onClose(){
        websocketMap.remove(uid);
        System.out.println("连接关闭");
    }

    @OnMessage
    public void onMeassage(String mes){
        JSONObject data = JSON.parseObject(mes);
        WebSocket toWebsocket = websocketMap.get(data.getString("to"));
        toWebsocket.sendMessage(data.getString("content"));
    }

    public void sendMessage(String mes){
        try {
            this.session.getBasicRemote().sendText(mes);
        }catch (IOException e){
            e.printStackTrace();
        }

    }



}
