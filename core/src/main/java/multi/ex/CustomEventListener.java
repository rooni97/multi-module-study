package multi.ex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomEventListener {

    @EventListener
    public void sendPush(CustomEvent event) {
        log.info("{} 이름의 이벤트 발생", event.getEventName());
    }
}
