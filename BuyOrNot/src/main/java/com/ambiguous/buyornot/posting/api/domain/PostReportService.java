package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.storage.PostReportRepository;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostReportService {

    private static final int REPORT_LIMIT = 15;

    private final PostRepository postRepository;
    private final PostReportRepository postReportRepository;

    public void report(Long postId, Long userId, ReportType type, String reason) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (postReportRepository.findByPostIdAndUserId(postId, userId).isPresent()) {
            throw new IllegalStateException("이미 신고한 게시글입니다.");
        }

        if (post.isDeleted()) {
            throw new IllegalStateException("이미 제재된 게시글입니다.");
        }

        postReportRepository.save(new PostReport(postId, userId, type, reason));

        long reportCount = postReportRepository.countByPostId(postId);

        if (reportCount >= REPORT_LIMIT) {
            post.softDelete();
            postRepository.save(post);
        }
    }

    public void cancel(Long postId, Long userId) {

        PostReport report = postReportRepository
                .findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new IllegalArgumentException("신고 내역이 존재하지 않습니다."));

        postReportRepository.delete(report);
    }
}
