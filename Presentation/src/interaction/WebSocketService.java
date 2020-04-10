package interaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint("/wsTchat")
public class WebSocketService {
	
	private static Map<String, Session> sessions = new HashMap<String, Session>();
	
	public WebSocketService() {
		System.out.println("Constructeur");
	}
	@OnOpen
	public void onOpen(Session session){
		System.out.println(session.getId() + " : ouverture connection");
		System.out.println("session="+session);
		try {
			sessions.put(session.getId(), session);
			session.getBasicRemote().sendText("Connection étabie.");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	@OnClose
	public void onClose(Session session){
		System.out.println("Session " +session.getId()+" has ended");
	}
	@OnMessage
	public void onMessage(String message, Session session){
		System.out.println("Message from " + session.getId() + ": " + message);
		try {
			for(Session s : sessions.values()) {
				s.getBasicRemote().sendText(message);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	@OnError
	public void onError(Throwable e){
		e.printStackTrace();
	}
}
