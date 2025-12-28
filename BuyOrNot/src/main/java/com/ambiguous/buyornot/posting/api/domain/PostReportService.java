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

    private final PostRepository postRepository;
    private final PostReportRepository postReportRepository;

    public void report(Long postId, Long reporterId, ReportType type, String reason) {
        postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (postReportRepository.findByPostIdAndUserId(postId, reporterId).isPresent()) {
            throw new IllegalStateException("이미 신고한 게시글입니다.");
        }

        PostReport report = new PostReport(postId, reporterId, type, reason);
        postReportRepository.save(report);
    }

    public void cancel(Long postId, Long reporterId) {

        PostReport report = postReportRepository
                .findByPostIdAndUserId(postId, reporterId)
                .orElseThrow(() -> new IllegalArgumentException("신고 내역이 존재하지 않습니다."));

        postReportRepository.delete(report);
    }
}
