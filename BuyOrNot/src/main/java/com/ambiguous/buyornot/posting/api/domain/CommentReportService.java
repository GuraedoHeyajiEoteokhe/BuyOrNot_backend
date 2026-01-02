package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.storage.CommentReportRepository;
import com.ambiguous.buyornot.posting.storage.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentReportService {

    private static final int REPORT_LIMIT = 15;

    private final CommentRepository commentRepository;
    private final CommentReportRepository commentReportRepository;

    public void report(Long commentId, Long userId, ReportType type, String reason) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        if (commentReportRepository.findByCommentIdAndUserId(commentId, userId).isPresent()) {
            throw new IllegalStateException("이미 신고한 댓글입니다.");
        }

        commentReportRepository.save(new CommentReport(commentId, userId, type, reason));

        long reportCount = commentReportRepository.countByCommentId(commentId);

        if (reportCount >= REPORT_LIMIT) {
            comment.softDeleteByReport();
            commentRepository.save(comment);
        }
    }

    public void cancel(Long commentId, Long userId) {

        CommentReport report = commentReportRepository
                .findByCommentIdAndUserId(commentId, userId)
                .orElseThrow(() -> new IllegalArgumentException("신고 내역이 존재하지 않습니다."));

        commentReportRepository.delete(report);
    }
}
