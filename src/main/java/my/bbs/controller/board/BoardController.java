package my.bbs.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.bbs.dto.board.BoardAddRequest;
import my.bbs.dto.board.BoardResponse;
import my.bbs.dto.board.BoardUpdateRequest;
import my.bbs.service.board.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
   private final BoardService boardService;

    /**
     * 게시글 전체 조회 핸들러
     * @return
     */
   @GetMapping("")
   public ResponseEntity<List<BoardResponse>> findAllBoards() {
       List<BoardResponse> allBoards = boardService.findAllBoards();

       return ResponseEntity.ok(allBoards);
   }

    /**
     * 게시글 조회 핸들러
     * @param boardId
     * @return
     */
   @GetMapping("/{boardId}")
   public ResponseEntity<BoardResponse> findBoard(@PathVariable Long boardId) {
       BoardResponse board = boardService.findBoard(boardId);

       return ResponseEntity.ok(board);
   }

    /**
     * 게시글 등록 핸들러
     * @param request
     * @return
     */
   @PostMapping("")
   public ResponseEntity<Void> addBoard(@RequestBody BoardAddRequest request) {
       boardService.addBoard(request);

       return ResponseEntity.created(null).build();
   }

    /**
     * 게시글 수정 핸들러
     * @param boardId
     * @param request
     * @return
     */
   @PutMapping("/{boardId}")
   public ResponseEntity<Void> updateBoard(@PathVariable Long boardId, @RequestBody BoardUpdateRequest request) {
       boardService.updateBoard(boardId, request);

       return ResponseEntity.noContent().build();
   }

    /**
     * 게시글 삭제 핸들러
     * @param boardId
     * @return
     */
   @DeleteMapping("/{boardId}")
   public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
       boardService.deleteBoard(boardId);

       return ResponseEntity.noContent().build();
   }

}
