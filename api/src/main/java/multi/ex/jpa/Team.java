package multi.ex.jpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@TableGenerator(name = "TEAM_SEQ_GEN", allocationSize = 50)
public class Team {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "TEAM_SEQ_GEN")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Team(String name) {
        this.name = name;
    }
}
