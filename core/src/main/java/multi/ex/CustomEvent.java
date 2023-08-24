package multi.ex;

import lombok.Getter;

@Getter
public class CustomEvent {

    private String eventName;

    public CustomEvent(String eventName) {
        this.eventName = eventName;
    }
}
