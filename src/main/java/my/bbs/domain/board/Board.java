package my.bbs.domain.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import my.bbs.domain.member.Member;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("제목")
    @Column(nullable = false)
    private String title;

    @Comment("내용")
    @Column(nullable = false)
    private String content;

    @Comment("작성자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private Member author; // N:1 관계 매핑
}
