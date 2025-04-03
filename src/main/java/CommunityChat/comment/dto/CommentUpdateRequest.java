package CommunityChat.comment.dto;

public record CommentUpdateRequest(
        String content,
        Long commentId
) {
}
