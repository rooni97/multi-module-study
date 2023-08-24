package multi.ex.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/save")
    public Long save(@RequestParam String name) {
        log.info("트랜잭션 시작");
        Long savedId = memberService.save(name);
        log.info("트랜잭션 종료");
        return savedId;
    }

    @GetMapping("/members")
    public List<Member> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/members/mt")
    public void saveWithMultiThread(@RequestParam Integer numOfThread) {
        ExecutorService executorService = Executors.newFixedThreadPool(numOfThread);
        for (int i = 1; i <= numOfThread; i++) {
            int finalI = i;
            executorService.execute(() -> save(String.valueOf(finalI)));
        }
    }
}
