package multi.ex.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RabbitController {

    private final RabbitTemplate rabbitTemplate;

    @MessageMapping("chat.enter.{roomId}")
    public Message enterUser(@DestinationVariable Long roomId, @Payload Message message) {
        rabbitTemplate.convertAndSend("chat.exchange", "enter.room." + roomId, message);
        return message;
    }

    @MessageMapping("chat.talk.{roomId}")
    public Message talkUser(@DestinationVariable Long roomId, @Payload Message message) {
        rabbitTemplate.convertAndSend("chat.exchange", "*.room." + roomId, message);
        return message;
    }

    @MessageMapping("chat.exit.{roomId}")
    public Message exitUser(@DestinationVariable Long roomId, @Payload Message message) {
        rabbitTemplate.convertAndSend("chat.exchange", "exit.room." + roomId, message);
        return message;
    }
}
