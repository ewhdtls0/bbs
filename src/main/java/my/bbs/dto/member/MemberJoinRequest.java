package my.bbs.dto.member;

import lombok.Data;
import my.bbs.domain.member.enums.Gender;

@Data
public class MemberJoinRequest {
    private String account;
    private String password;
    private String name;
    private Gender gender;
}
