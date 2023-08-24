package multi.ex.websocket;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Message {

    private String type;
    private String comment;
    private String sender;
    private Long roomId;

    @Builder
    public Message(String type, String comment, String sender, Long roomId) {
        this.type = type;
        this.comment = comment;
        this.sender = sender;
        this.roomId = roomId;
    }


    public void modifyComment(String comment) {
        this.comment = comment;
    }
}
