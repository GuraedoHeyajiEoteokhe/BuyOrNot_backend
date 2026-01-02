package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.posting.api.domain.CommentReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentReportRepository extends JpaRepository<CommentReport, Long> {

    Optional<CommentReport> findByCommentIdAndUserId(Long postId, Long userId);
    long countByCommentId(Long postId);
}
