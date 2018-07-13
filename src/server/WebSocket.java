package server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import vo.Message;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ author: 杨浩麟
 * @ date: 2018/7/10 10:04
 */
@ServerEndpoint("/websocket/{id}")
public class WebSocket {
    private static ConcurrentHashMap<String,CopyOnWriteArraySet<WebSocket>> websocketMap = new ConcurrentHashMap<>();

    private Session session;
    private String uid;

    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session){
        this.session = session;
        this.uid = id;
        if(websocketMap.get(uid)!=null)
            websocketMap.get(uid).add(this);
        else {
            CopyOnWriteArraySet<WebSocket> set = new CopyOnWriteArraySet<>();
            set.add(this);
            websocketMap.put(uid,set);
        }
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
        CopyOnWriteArraySet<WebSocket> toWebsocket = websocketMap.get(data.getString("to"));
        if(toWebsocket!=null){
            for(WebSocket ws:toWebsocket)
                ws.sendMessage(data.getString("content"),uid);
        }
        Message.insertMessage(uid,data.getString("to"),data.getString("content"),0);
    }

    public void sendMessage(String mes,String from){
        try {
            Map<String,String> map = new HashMap<>();
            map.put("send",from);
            map.put("content",mes);
            String m = JSON.toJSONString(map);
            this.session.getBasicRemote().sendText(m);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void sendSystemMessage(String mes,String to){
        CopyOnWriteArraySet<WebSocket> toWebsocket = websocketMap.get(to);
        if(toWebsocket!=null){
            for(WebSocket ws:toWebsocket)
                ws.sendMessage(mes,"System");
        }
        Message.insertMessage("System",to,mes,0);
    }



}
