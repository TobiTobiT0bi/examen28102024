package com.teruel.examen.service;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class ChatHandler extends TextWebSocketHandler {
	private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();
	private ArrayList<TextMessage> messages = new ArrayList<TextMessage>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		sessions.add(session);
		for (TextMessage message : messages) {
            session.sendMessage(message);
        }
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		sessions.remove(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub	
		if(message.getPayload().toString().contains("websocket")) {
			session.sendMessage(new TextMessage(""
					+ "WebSocket es un protocolo de comunicación que permite la comunicación bidireccional entre un cliente (por ejemplo, un navegador web) "
					+ "y un servidor a través de una única conexión persistente. A diferencia de las peticiones HTTP tradicionales, donde el cliente debe hacer "
					+ "una solicitud para recibir una respuesta, WebSocket permite que ambos lados (cliente y servidor) puedan enviar mensajes en tiempo real sin "
					+ "tener que volver a establecer la conexión cada vez. esto en las peticiones tradicionales no ocurre, ya que por cada peticion se debe establecer "
					+ "una nueva conexion.\r\n"
					+ ""
					+ ""
					+ "WebSocket suele ejecutarse en los puertos 80 y 443. Estos son los mismos puertos utilizados por HTTP y HTTPS, respectivamente.\r\n"
					+ "Puerto 80: Para conexiones WebSocket sin cifrar (ws://).\r\n"
					+ "Puerto 443: Para conexiones WebSocket cifradas (wss://).\r\n"
					+ ""));
		} else {
			messages.add(message);
			for (WebSocketSession webSocketSession : sessions) {
	            webSocketSession.sendMessage(message);
	        }
		}
	}
}
