package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.api.controller.request.CreateCommentRequest;
import com.ambiguous.buyornot.posting.api.controller.response.CommentResponse;
import com.ambiguous.buyornot.posting.storage.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPostId(Long postId) {

        List<Comment> comments = commentRepository.findByPostIdOrderByCreatedAtAsc(postId);

        Map<Long, CommentResponse> map = new HashMap<>();
        List<CommentResponse> roots = new ArrayList<>();

        // record 생성
        for (Comment comment : comments) {
            map.put(comment.getId(), CommentResponse.from(comment));
        }

        // 트리 연결
        for (Comment comment : comments) {
            CommentResponse current = map.get(comment.getId());

            if (comment.getParent() == null) {
                roots.add(current);
            } else {
                CommentResponse parent = map.get(comment.getParent().getId());
                parent.children().add(current);
            }
        }

        return roots;
    }
}
