package server.share;

import DAO.MessageDAO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
@ServerEndpoint("/websocket/{id}/{type}")
public class WebSocket {
    private static ConcurrentHashMap<String,CopyOnWriteArraySet<WebSocket>> websocketMap = new ConcurrentHashMap<>();

    private Session session;
    private String uid;
    private int type;

    @OnOpen
    public void onOpen(@PathParam("id") String id,@PathParam("type") int type, Session session){
        this.session = session;
        this.uid = id;
        this.type=type;
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
        websocketMap.get(uid).remove(this);
        if(websocketMap.get(uid).size()==0) websocketMap.remove(uid);
        System.out.println("连接关闭");
    }

    @OnMessage
    public void onMeassage(String mes){
        JSONObject data = JSON.parseObject(mes);
        CopyOnWriteArraySet<WebSocket> toWebsocket = websocketMap.get(data.getString("to"));
        MessageDAO.insertMessage(uid,data.getString("to"),data.getString("content"),0);
        if(toWebsocket!=null){
            for(WebSocket ws:toWebsocket)
                ws.sendMessage(data.getString("content"),uid);
        }

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
        MessageDAO.insertMessage("System",to,mes,0);
    }

    public static void sendMes2Head(String uid,int reduce){
        CopyOnWriteArraySet<WebSocket> toWebsocket = websocketMap.get(uid);
        for(WebSocket ws:toWebsocket){
            if(ws.type!=0){
                Map<String,String> map =new HashMap<>();
                map.put("reduce",String.valueOf(reduce));
                String m = JSON.toJSONString(map);
                try {
                    ws.session.getBasicRemote().sendText(m);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }



}
