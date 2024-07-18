package my.bbs.dto.member;

import lombok.Data;
import my.bbs.domain.member.enums.Gender;

@Data
public class MemberResponse {
    private Long memberId;
    private String account;
    private String name;
    private Gender gender;
}
