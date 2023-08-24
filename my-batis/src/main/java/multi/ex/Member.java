package multi.ex;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Member {

    private Long id;

    private String name;

    private Long team_id;
}
