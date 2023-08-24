package multi.ex.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;

//@Service
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final Sessions sessions;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(payload);
        String type = jsonNode.get("type").asText();
        long roomId = jsonNode.get("roomId").asLong();
        String sender = jsonNode.get("sender").asText();
        String comment = jsonNode.get("comment").asText();

        Message chatMessage = Message.builder()
                .type(type)
                .comment(comment)
                .sender(sender)
                .roomId(roomId)
                .build();

        handleActions(session, chatMessage);
    }

    private void handleActions(WebSocketSession session, Message chatMessage) {
        if (chatMessage.getType().equals("ENTER")) {
            chatMessage.modifyComment(chatMessage.getSender() + "님이 입장했습니다.");
            Set<WebSocketSession> roomSessions = sessions.getSessionsByRoomId(chatMessage.getRoomId());
            roomSessions.add(session);
            sendMessage(chatMessage);
        } else if (chatMessage.getType().equals("TALK")) {
            sendMessage(chatMessage);
        } else if (chatMessage.getType().equals("EXIT")) {
            chatMessage.modifyComment(chatMessage.getSender() + "님이 퇴장했습니다.");
            sendMessage(chatMessage);
            sessions.removeSession(session);
        }
    }

    private void sendMessage(Message chatMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<WebSocketSession> roomSessions = sessions.getSessionsByRoomId(chatMessage.getRoomId());
        roomSessions.parallelStream().forEach(s -> {
            try {
                s.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
