package CommunityChat.comment;

import CommunityChat.comment.dto.CommentListResponse;
import CommunityChat.comment.dto.CommentRequest;
import CommunityChat.comment.dto.CommentResponse;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public CommentResponse create(@RequestBody CommentRequest request) {
        return commentService.create(request);
    }

    @GetMapping("/comments/{postId}")
    public CommentListResponse findByPostId(@PathVariable Long postId) {
        return commentService.findByPostId(postId);
    }
}
