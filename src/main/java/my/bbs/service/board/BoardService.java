package my.bbs.service.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.bbs.domain.board.Board;
import my.bbs.domain.member.Member;
import my.bbs.dto.board.BoardAddRequest;
import my.bbs.dto.board.BoardResponse;
import my.bbs.dto.board.BoardUpdateRequest;
import my.bbs.repository.board.BoardRepository;
import my.bbs.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시글 등록
     * @param request
     */
    public void addBoard(BoardAddRequest request) {
        Board boardEntity = new Board();

        Member findMember = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("멤버 정보가 없습니다."));

        boardEntity.setAuthor(findMember);
        boardEntity.setTitle(request.getTitle());
        boardEntity.setContent(request.getContent());

        boardRepository.save(boardEntity);
    }

    /**
     * 게시글 조회
     * @param boardId
     * @return
     */
    public BoardResponse findBoard(Long boardId) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글 정보가 없습니다."));

        BoardResponse response = new BoardResponse();
        response.setBoardId(findBoard.getId());
        response.setMemberId(findBoard.getAuthor().getId());
        response.setTitle(findBoard.getTitle());
        response.setContent(findBoard.getContent());

        return response;
    }

    /**
     * 게시글 전체 조회
     * @return
     */
    public List<BoardResponse> findAllBoards() {
        return boardRepository.findAll()
                .stream()
                .map(board -> {
                    BoardResponse response = new BoardResponse();
                    response.setBoardId(board.getId());
                    response.setMemberId(board.getAuthor().getId());
                    response.setTitle(board.getTitle());
                    response.setContent(board.getContent());

                    return response;
                }).toList();
    }

    /**
     * 게시글 수정
     * @param boardId
     * @param request
     */
    public void updateBoard(Long boardId, BoardUpdateRequest request) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글 정보가 없습니다."));

        if(StringUtils.hasText(request.getTitle()))
            findBoard.setTitle(request.getTitle());

        if(StringUtils.hasText(request.getContent()))
            findBoard.setContent(request.getContent());
    }

    /**
     * 게시글 삭제
     * @param boardId
     */
    public void deleteBoard(Long boardId) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글 정보가 없습니다."));

        boardRepository.delete(findBoard);
    }
}
