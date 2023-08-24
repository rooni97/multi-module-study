package multi.ex;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final ApplicationEventPublisher eventPublisher;
    
    @GetMapping("/test")
    public String test(@RequestParam String eventName) {
        eventPublisher.publishEvent(new CustomEvent(eventName));
        return testService.test();
    }
}
