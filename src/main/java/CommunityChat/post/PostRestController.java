package CommunityChat.post;

import org.springframework.web.bind.annotation.*;

@RestController
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/boards/{boardId}/posts")
    public PostResponse create(@PathVariable Long boardId,
                               @RequestBody CreatePostRequest request){
        return postService.create(boardId, request);
    }

    @GetMapping("/boards/{boardId}/posts/{postId}")
    public PostResponse findPost(@PathVariable Long boardId,
                                 @PathVariable Long postId){
        return postService.findPost(boardId, postId);
    }

    @PutMapping("/boards/{boardId}/posts/{postId}")
    public PostResponse update(@PathVariable Long boardId,
                               @PathVariable Long postId,
                               @RequestBody CreatePostRequest request){
        return postService.update(boardId, postId, request);
    }

    @DeleteMapping("/boards/{boardId}/posts/{postId}")
    public void delete(@PathVariable Long boardId,
                       @PathVariable Long postId){
        postService.delete(boardId, postId);
    }

}
