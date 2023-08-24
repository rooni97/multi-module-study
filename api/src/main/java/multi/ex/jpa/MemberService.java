package multi.ex.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(String name) {
        memberRepository.findAll();
        Member member = new Member(name);
        log.info("SAVE START");

        Member savedMember = memberRepository.save(member);
        log.info("SAVE END");
        if (name.equals("ex")) {
            throw new RuntimeException("go");
        }
        memberRepository.findById(20000L);
        return savedMember.getId();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    
    
}
