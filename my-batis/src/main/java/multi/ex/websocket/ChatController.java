package multi.ex.websocket;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chatGET() {
        return "chat";
    }

    @MessageMapping("/chat/enter/{roomId}")
    @SendTo("/sub/chat/room/{roomId}")
    public String enterUser(@DestinationVariable Long roomId, @Payload Message message) {
        message.modifyComment(message.getSender() + "님이 채팅방에 입장하였습니다.");
        return message.getComment();
    }

    @MessageMapping("/chat/talk/{roomId}")
    @SendTo("/sub/chat/room/{roomId}")
    public String talkUser(@DestinationVariable Long roomId, @Payload Message message) {
        return message.getComment();
    }

    @MessageMapping("/chat/exit/{roomId}")
    @SendTo("/sub/chat/room/{roomId}")
    public String exitUser(@DestinationVariable Long roomId, @Payload Message message) {
        message.modifyComment(message.getSender() + "님이 채팅방에 입장하였습니다.");
        return message.getComment();
    }
}
