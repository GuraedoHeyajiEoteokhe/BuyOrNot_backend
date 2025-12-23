package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.api.controller.request.CreateCommentRequest;
import com.ambiguous.buyornot.posting.storage.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public void createComment(Long postId, Long userId, String userNickname, CreateCommentRequest request) {

        Comment parent = null;

        // 대댓글인 경우
        if (request.parentId() != null) {
            parent = commentRepository.findById(request.parentId())
                    .orElseThrow(() -> new IllegalArgumentException("부모 댓글이 존재하지 않습니다."));

            if (!parent.getPostId().equals(postId)) {
                throw new IllegalArgumentException("부모 댓글이 다른 게시글에 속해 있습니다.");
            }

            if (parent.isDeleted()) {
                throw new IllegalStateException("삭제된 댓글에는 대댓글을 작성할 수 없습니다.");
            }
        }

        Comment comment = request.toEntity(postId, userId, userNickname, parent);
        commentRepository.save(comment);
    }
}
