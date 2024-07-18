package my.bbs.dto.board;

import lombok.Data;

@Data
public class BoardResponse {
    private Long boardId;
    private Long memberId;
    private String title;
    private String content;
}
