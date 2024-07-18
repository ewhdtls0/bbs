package my.bbs.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import my.bbs.domain.member.enums.Gender;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("계정")
    @Column(nullable = false)
    private String account;

    @Comment("비밀번호")
    @Column(nullable = false)
    private String password;

    @Comment("이름")
    @Column(nullable = false)
    private String name;
    
    @Comment("성별")
    @Enumerated(EnumType.STRING) // enum의 순서가 아닌 문자 값 할당
    private Gender gender;
}
