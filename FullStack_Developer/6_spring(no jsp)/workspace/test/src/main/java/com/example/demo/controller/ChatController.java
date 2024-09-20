package com.example.demo.controller;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@ServerEndpoint("/chat")
public class ChatController {

    // Set to store WebSocket sessions (connected clients)
    private static Set<Session> clients = new HashSet<>();

    // Method to handle new client connections
    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);  // Add session to the connected clients set
        System.out.println("New connection: " + session.getId());
    }

    // Method to handle incoming messages from clients
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Received message from " + session.getId() + ": " + message);
        
        // Broadcast message to all connected clients
        for (Session client : clients) {
            if (client.isOpen()) {
                client.getBasicRemote().sendText(message);
            }
        }
    }

    // Method to handle client disconnection
    @OnClose
    public void onClose(Session session) {
        clients.remove(session);  // Remove session from the connected clients set
        System.out.println("Connection closed: " + session.getId());
    }
}