package multi.ex;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String test() {
        return "core 모듈의 Bean Class 테스트";
    }
}
