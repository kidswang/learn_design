package com.waiwaiwai.protocol.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/11/23 9:22
 * @Description: websocket服务端
 */
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketServer {
    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static Map<String, Session> clients = new ConcurrentHashMap<String, Session>();

    // 第一次连接时触发
    // 可以记录用户连接时使用的参数
    // 也可以统计用户的连接数
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) throws IOException {
        if (!clients.containsKey(userId)) {
            clients.put(userId, session);
        }
        ArrayList<String> keyList = new ArrayList<>();
        for (String key : clients.keySet()) {
            keyList.add(key);
        }
        logger.info("在线人数为：" + keyList.size());
        sendInfo("message", userId);
    }


    @OnMessage
    public void onMessage(@PathParam("userId") String userId, Session session, String message) throws IOException {
        logger.info("用户" + userId + "的消息为：" + message);
        logger.info("用户" + userId + "的session为：" + session.toString());
        session.getBasicRemote().sendText(message);
    }

    @OnClose
    public void onCLose(@PathParam("userId") String userId) {
        clients.remove(userId);
        logger.info("用户" + userId + "关闭了连接");
        ArrayList<String> keyList = new ArrayList<>();
        for (String key : clients.keySet()) {
            keyList.add(key);
        }
        logger.info("在线人数为：" + keyList.size());
    }

    @OnError
    public void onError(Throwable error) {
        logger.info("错误信息：" + error.getMessage());
        error.printStackTrace();
    }

    public void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        System.out.println("推送消息到" + userId + "，推送内容:" + message);
        Session session = clients.get(userId);
        if (session != null) {
            synchronized (session) {
                session.getBasicRemote().sendText(message);
            }
        }

    }
}
