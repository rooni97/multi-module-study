package multi.ex.jpa;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    public MemberService memberService;

    @Autowired
    public MemberRepository memberRepository;

    @Autowired
    public HikariDataSource dataSource;

    @Autowired
    public EntityManager em;

    @Test
    public void saveFail() {
        HikariConfigMXBean config = dataSource.getHikariConfigMXBean();
        int maximumPoolSize = 1;
        config.setMaximumPoolSize(maximumPoolSize);
        int maximumTaskThread = 1;

        memberService.save("name");
        List<Member> members = memberRepository.findAll();

        assertThat(members.size()).isNotEqualTo(maximumTaskThread);
        assertThat(members.size()).isEqualTo(0);
    }

    @Test
    public void saveWithMultiThreadFail() {
        HikariConfigMXBean config = dataSource.getHikariConfigMXBean();
        int maximumPoolSize = 5;
        config.setMaximumPoolSize(maximumPoolSize);
        int maximumTaskThread = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(maximumTaskThread);

        for (int i = 0; i < maximumTaskThread; i++) {
            executorService.execute(() -> memberService.save("name"));
        }

        List<Member> members = memberRepository.findAll();

        assertThat(members.size()).isNotEqualTo(maximumTaskThread);
        assertThat(members.size()).isEqualTo(0);

    }

    @Test
    public void saveWithMultiThreadSuccess() {
        HikariConfigMXBean config = dataSource.getHikariConfigMXBean();
        int maximumPoolSize = 5;
        config.setMaximumPoolSize(maximumPoolSize);
        int maximumTaskThread = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(maximumTaskThread);

        for (int i = 0; i < maximumTaskThread; i++) {
            executorService.execute(() -> memberService.save("name"));
        }

        List<Member> members = memberRepository.findAll();

        assertThat(members.size()).isNotEqualTo(0);
        assertThat(members.size()).isEqualTo(maximumTaskThread);
    }

}