package my.bbs.dto.board;

import lombok.Data;

@Data
public class BoardUpdateRequest {
    private String title;
    private String content;
}
