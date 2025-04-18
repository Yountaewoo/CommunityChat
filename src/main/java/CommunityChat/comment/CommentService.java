package CommunityChat.comment;

import CommunityChat.comment.dto.CommentListResponse;
import CommunityChat.comment.dto.CommentRequest;
import CommunityChat.comment.dto.CommentResponse;
import CommunityChat.comment.dto.CommentUpdateRequest;
import CommunityChat.post.Post;
import CommunityChat.post.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentResponse create(CommentRequest request) {

        Post post = postRepository.findById(request.postId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 게시글이 없습니다."));
        Comment comment = commentRepository.save(new Comment(request.content(), post));
        return new CommentResponse(comment.getContent(), comment.getId());
    }

    public CommentListResponse findByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return new CommentListResponse(
                comments
                        .stream()
                        .map(comment -> new CommentResponse(comment.getContent(), comment.getId()))
                        .toList());
    }

    @Transactional
    public CommentResponse update(CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(request.commentId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 댓글이 없습니다."));
        comment.update(request.content());
        return new CommentResponse(comment.getContent(), comment.getId());
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
