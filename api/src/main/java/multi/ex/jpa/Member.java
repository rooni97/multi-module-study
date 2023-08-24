package multi.ex.jpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@TableGenerator(name = "MEMBER_SEQ_GEN", allocationSize = 1)
public class Member {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "MEMBER_SEQ_GEN")
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public Member(String name) {
        this.name = name;
    }

    public Member(String name, Team team) {
        this.name = name;
        this.team = team;
    }
}
