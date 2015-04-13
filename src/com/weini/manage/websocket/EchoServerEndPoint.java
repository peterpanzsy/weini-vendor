package com.weini.manage.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint gives the relative name for the end point
 * This will be accessed via ws://localhost:8080/EchoChamber/echo
 * Where "localhost" is the address of the host,
 * "EchoChamber" is the name of the package
 * and "echo" is the address to access this class from the server
 */
@ServerEndpoint("/echo")
public class EchoServerEndPoint {
//    private static final AtomicInteger connectionIds = new AtomicInteger(0);
  /*  private static final Set<EchoServerEndPoint> connections =
            new CopyOnWriteArraySet<EchoServerEndPoint>();*/
    public static final Map<Integer,Set<EchoServerEndPoint>> connectionMap = new HashMap<Integer,Set<EchoServerEndPoint>>();

    public Session session;
    private int vendorId;

    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was
     * successful.
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        Map<String,List<String>> paramMap=session.getRequestParameterMap();
        this.vendorId = Integer.parseInt(paramMap.get("vendorId").get(0));

        if(connectionMap.containsKey(vendorId)){//若该商家的会话已经存在，则直接取出，并添加当前会话
            Set<EchoServerEndPoint> connections = connectionMap.get(vendorId);
            connections.add(this);
            connectionMap.put(vendorId,connections);
        }else{//新建该商家的管理系统会话集合
            Set<EchoServerEndPoint> connections =
                    new CopyOnWriteArraySet<EchoServerEndPoint>();
            connections.add(this);
            connectionMap.put(vendorId,connections);
        }

        System.out.println(session.getId() + " has opened a connection");
        try {
            session.getBasicRemote().sendText("Connection Established");
            while(true){
                session.getBasicRemote().sendText(session.getId());
                session.getBasicRemote().sendText(connectionMap.keySet().toString());
                //this.wait(3000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            connectionMap.get(vendorId).remove(this);
            try {
                session.close();
            } catch (Exception e) {
                System.out.print("2");
                e.printStackTrace();
            }
        }
    }

    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * The user closes the connection.
     *
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        connectionMap.get(this.vendorId).remove(this);
        System.out.println("Session " +session.getId()+" has ended");
    }
}