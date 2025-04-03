package CommunityChat.comment.dto;

import java.util.List;

public record CommentListResponse(
        List<CommentResponse> list
) {
}
