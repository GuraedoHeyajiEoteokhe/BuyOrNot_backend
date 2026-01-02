package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "tbl_comment_report",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"comment_id", "user_id"})
        }
)
public class CommentReport extends BaseEntity {

    @Column(nullable = false)
    private Long commentId;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportType type;

    @Column(length = 500)
    private String reason;

    public CommentReport(Long commentId, Long userId, ReportType type, String reason) {
        this.commentId = commentId;
        this.userId = userId;
        this.type = type;
        this.reason = reason;
    }
}
