package CommunityChat.post;

import CommunityChat.board.Board;
import CommunityChat.board.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    //TODO: 생성, 수정, 삭제
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    public PostService(BoardRepository boardRepository, PostRepository postRepository) {
        this.boardRepository = boardRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public PostResponse create(Long boardId, CreatePostRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("선택한 게시판(카테고리)이 존재하지 않음"));

        Post post = new Post(
                request.title(),
                request.content(),
                board  //게시글 생성 시 게시판과의 연관관계 설정
        );
        postRepository.save(post);

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent());
    }

    //TODO: 게시글 조회(시간순 정렬, 조회순 정렬, 게시글 제목 & 게시글 내용 (키워드) 로 검색 )
//    public List<PostResponse> findAll() {
//    }

    //게시글 상세 조회
    // 게시판 - 게시글 목록 - 게시글 선택이므로 게시판에 속하는지 검증해야함
    public PostResponse findPost(Long boardId,
                                 Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 조회할 수 없음"));

        if (!post.getBoard().getId().equals(boardId)){
            throw new IllegalArgumentException( "해당 게시판에 속한 게시글 아님");
        }
        return new PostResponse(post.getId(),
                post.getTitle(),
                post.getContent());

    }

    @Transactional
    public PostResponse update(Long boardId,
                               Long postId,
                               CreatePostRequest request) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("선택한 게시글 존재하지 않음"));

        if (!post.getBoard().getId().equals(boardId)) {
            throw new IllegalArgumentException("해당 게시판에 속한 게시글 아님, 수정 불가");
        }
        post.updatePost(
                request.title(),
                request.content());

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent());
    }

    public void delete(Long boardId,
                       Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));

        if (!post.getBoard().getId().equals(boardId)){
            throw new IllegalArgumentException("해당 게시판에 속한 게시글 아님, 삭제 불가");
        }
        postRepository.deleteById(postId);
    }

}
