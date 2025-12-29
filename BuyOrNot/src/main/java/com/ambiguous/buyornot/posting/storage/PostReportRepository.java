package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.posting.api.domain.PostReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostReportRepository extends JpaRepository<PostReport, Long> {

    Optional<PostReport> findByPostIdAndUserId(Long postId, Long userId);
    long countByPostId(Long postId);

    void deleteByPostId(Long postId);
}
