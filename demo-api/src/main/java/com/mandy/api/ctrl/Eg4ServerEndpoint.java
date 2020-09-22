package com.mandy.api.ctrl;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value="/real-time-clock")
public class Eg4ServerEndpoint {

	private final SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
	
	private Thread clockThread;
	private boolean isStarted = false;
	
	
    @OnOpen
    public void start(Session session) {
        final Session clockSession = session;
        
        isStarted = true;
        clockThread = new Thread(() -> {
                while (isStarted) {
                	String dateStr = sdf.format(new Date());
                    String name = Thread.currentThread().getName();
                    try {
                    	System.out.println(dateStr + "-test:" + name);
                    	clockSession.getBasicRemote().sendText(dateStr);
                    	Thread.sleep(1000);
                    } catch (IOException | InterruptedException ie) {
                    	isStarted = false;
                    }
                }
        });
        clockThread.start();
    }

    @OnMessage
    public String stopFromUser(String message) {
        if ("stop".equals(message)) {
            this.stop();
            return "stopped";
        } else {
            return "ERROR MESSAGE:" + message;
        }
    }

    @OnError
    public void error(Throwable t) {
        this.stop() ;
    }

    @OnClose
    public void stop() {
        isStarted = false;
        clockThread = null;
    }
}