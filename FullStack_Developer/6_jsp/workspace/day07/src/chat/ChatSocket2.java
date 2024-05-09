package chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@ServerEndpoint("/chat2/{userid}")
public class ChatSocket2 {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	private static Map<Session, String> client_id = Collections.synchronizedMap(new HashMap<Session, String>());
	private static List<ChatRoom> rooms = Collections.synchronizedList(new ArrayList<ChatRoom>());
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException{
		Gson gson = new Gson();
		String command = message.split(" : ")[0];
		if(command.equals("create")) {
			//시간과 로그인 아이디로 pk 생성
			String pk = new Date().getTime()+client_id.get(session);
			//채팅방 객체 생성
			ChatRoom room = new ChatRoom();
			//채팅방 셋팅
			room.setPk(pk);
			room.setName(message.split(" : ")[1]);
			room.setMembers(new HashSet<String>());
			room.getMembers().add(session.getId());
			
			//채팅방 목록에 추가
			synchronized (rooms) {
				rooms.add(room);
			}
			synchronized (clients) {
				for(Session client : clients) {
					if(!client.equals(session)) {
						client.getBasicRemote().sendText("display : "+getRoomsInfo());
					}
				}
			}
		}
		else if(command.equals("enter")) {
			String pk = message.split(" : ")[1];
			ChatRoom enteredRoom = null;
			for(ChatRoom room : rooms) {
				if(room.getPk().equals(pk)) {
					enteredRoom = room;
					break;
				}
			}
			enteredRoom.getMembers().add(session.getId());
			synchronized (clients) {
				for(Session client : clients) {
					if(!client.equals(session)) {
						client.getBasicRemote().sendText("display : "+getRoomsInfo());
					}
				}
			}
		}
		else if(command.equals("display")) {
			session.getBasicRemote().sendText("display : "+getRoomsInfo());
		}
		else {
			synchronized (clients) {
				for(Session client : clients) {
					if(!client.equals(session)) {
						System.out.println("Send '"+message+"' To "+client_id.get(client));
						client.getBasicRemote().sendText(message);
					}
				}
			}
		}
	}
	@OnOpen
	public void onOpen(Session session, @PathParam(value="userid")String userid) throws IOException {
		System.out.println("접속 : "+session+" / 아이디 : "+userid);
		clients.add(session);
		client_id.put(session, userid);
		session.getBasicRemote().sendText("display : "+getRoomsInfo());
	}
	@OnClose
	public void onClose(Session session) throws IOException{
		List<ChatRoom> removed = new ArrayList<ChatRoom>();
		System.out.println("종료 : "+session+" / 아이디 : "+client_id.get(session));
		synchronized (rooms) {
			for(ChatRoom room : rooms) {
				room.getMembers().remove(session.getId());
				if(room.getMembers().size() == 0) {
					removed.add(room);
				}			
			}
			rooms.removeAll(removed);
		}
		clients.remove(session);
		client_id.remove(session);
		synchronized (clients) {
			for(Session client : clients) {
				if(!client.equals(session)) {
					client.getBasicRemote().sendText("display : "+getRoomsInfo());
				}
			}
		}
	}
	private String getRoomsInfo() {
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		for(int i=0;i<rooms.size();i++) {
			json.add(i+"", gson.toJsonTree(rooms.get(i)));
		}
		return json.toString();
	}
}





