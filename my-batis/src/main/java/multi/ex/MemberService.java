package multi.ex;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final TestMapper testMapper;

    public List<Member> getAllDataList() {
        return testMapper.getAllDataList();
    }

    public void saveOne(Long id, String name, Long teamId) {
        testMapper.saveOne(id, name, teamId);
    }
}
