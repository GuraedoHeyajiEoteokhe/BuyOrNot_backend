package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.posting.api.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostIdOrderByPinnedDescCreatedAtAsc(Long postId);
    Optional<Comment> findByPostIdAndPinnedTrue(Long postId);

    void deleteByPostId(Long postId);
}
