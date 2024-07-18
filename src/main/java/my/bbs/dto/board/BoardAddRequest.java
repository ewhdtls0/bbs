package my.bbs.dto.board;

import lombok.Data;

@Data
public class BoardAddRequest {
    private Long memberId;
    private String title;
    private String content;
}
