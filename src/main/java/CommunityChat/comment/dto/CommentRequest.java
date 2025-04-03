package CommunityChat.comment.dto;

public record CommentRequest(
        String content,
        Long postId
) {
}
