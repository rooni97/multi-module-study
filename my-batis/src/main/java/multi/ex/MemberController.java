package multi.ex;

import lombok.RequiredArgsConstructor;
import multi.ex.config.SampleConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/members")
    public List<Member> getAllDataList() {
        return memberService.getAllDataList();
    }

    @GetMapping("/members/save")
    public void saveOne(@RequestParam Long id, @RequestParam String name, @RequestParam Long team_id) {
        memberService.saveOne(id, name, team_id);
    }

    @GetMapping("/mybatis")
    public String test() {
        return "mybatis";
    }

    @GetMapping("/sample/queue")
    public String samplePublish(@RequestParam String message) {
        rabbitTemplate.convertAndSend(SampleConfig.EXCHANGE_NAME, SampleConfig.ROUTING_KEY, message);
        return "message sending!";
    }

}
