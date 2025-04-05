package CommunityChat.board;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardRestController {
    private final BoardService boardService;

    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards")
    public BoardResponse create(@RequestBody CreateBoardRequest request){
        return boardService.create(request);
    }

    @GetMapping("/boards")
    public List<BoardResponse> findAll(){
        return boardService.findAll();
    }

    @GetMapping("/boards/{boardId}")
    public BoardResponse findBoard(@PathVariable Long boardId){
        return boardService.findBoard(boardId);
    }

    @PutMapping("/boards/{boardId}")
    public BoardResponse update(@PathVariable Long boardId,
                                @RequestBody CreateBoardRequest request){
        return boardService.update(boardId, request);
    }

    @DeleteMapping("/boards/{boardId}")
    public void delete(@PathVariable Long boardId){
        boardService.delete(boardId);
    }


}
