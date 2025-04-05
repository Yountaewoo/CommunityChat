package CommunityChat.board;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponse create(CreateBoardRequest request) {
        Board board = new Board(request.title());
        boardRepository.save(board);

        return new BoardResponse(
                board.getId(),
                board.getTitle());
    }

    public List<BoardResponse> findAll() {
        return boardRepository.findAll()
                .stream()
                .map(board -> new BoardResponse(
                        board.getId(),
                        board.getTitle()))
                .toList();
    }

    public BoardResponse findBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판, 조회 불가"));

        return new BoardResponse(
                board.getId(),
                board.getTitle());

    }

    @Transactional
    public BoardResponse update(Long boardId, CreateBoardRequest request) {
        Board boardUpdate = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판, 수정 불가"));
        boardUpdate.updateTitle(request.title());

        return new BoardResponse(
                boardUpdate.getId(),
                boardUpdate.getTitle());
    }

    public void delete(Long boardId) {
        boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판, 삭제 불가"));

        boardRepository.deleteById(boardId);
    }
}
