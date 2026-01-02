package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.api.controller.request.CreateCommentRequest;
import com.ambiguous.buyornot.posting.api.controller.request.UpdateCommentRequest;
import com.ambiguous.buyornot.posting.api.controller.response.CommentResponse;
import com.ambiguous.buyornot.posting.storage.CommentRepository;
import com.ambiguous.buyornot.posting.storage.PostRepository;
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
    private final PostRepository postRepository;

    public void createComment(Long postId, CreateCommentRequest request) {

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

        Comment comment = request.toEntity(postId, parent);
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPostId(Long postId) {

        List<Comment> comments = commentRepository.findByPostIdOrderByPinnedDescCreatedAtAsc(postId);

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

    public void updateComment(Long commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        if (comment.isDeleted()) {
            throw new IllegalStateException("삭제된 댓글은 수정할 수 없습니다.");
        }

        if (!comment.getUserId().equals(request.userId())) {
            throw new IllegalStateException("댓글 수정 권한이 없습니다.");
        }

        comment.updateContent(request.content());
    }

    public void deleteComment(Long commentId, Long userId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        if (comment.isDeleted()) {
            throw new IllegalStateException("이미 삭제된 댓글입니다.");
        }

        if (!comment.getUserId().equals(userId)) {
            throw new IllegalStateException("댓글 삭제 권한이 없습니다.");
        }

        comment.softDeleteByUser();
    }

    public void pinComment(Long commentId, Long userId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        if (comment.isDeleted()) {
            throw new IllegalStateException("삭제된 댓글은 고정할 수 없습니다.");
        }

        if (comment.getParent() != null) {
            throw new IllegalStateException("대댓글은 고정할 수 없습니다.");
        }

        if (comment.isPinned()) {
            throw new IllegalStateException("이미 고정된 댓글입니다.");
        }

        Post post = postRepository.findById(comment.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (!post.getUserId().equals(userId)) {
            throw new IllegalStateException("게시글 작성자만 댓글을 고정할 수 있습니다.");
        }

        // 기존 고정 댓글 해제
        commentRepository.findByPostIdAndPinnedTrue(comment.getPostId())
                .filter(pinned -> !pinned.getId().equals(comment.getId()))
                .ifPresent(Comment::unpin);

        comment.pin();
    }

    public void unpinComment(Long commentId, Long userId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        if (comment.isDeleted()) {
            throw new IllegalStateException("삭제된 댓글은 고정 해제할 수 없습니다.");
        }

        if (!comment.isPinned()) {
            throw new IllegalStateException("고정되지 않은 댓글입니다.");
        }

        Post post = postRepository.findById(comment.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (!post.getUserId().equals(userId)) {
            throw new IllegalStateException("게시글 작성자만 댓글 고정을 해제할 수 있습니다.");
        }

        comment.unpin();
    }
}
