package multi.ex;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestMapper {

    List<Member> getAllDataList();

    void saveOne(Long id, String name, Long team_id);
}
