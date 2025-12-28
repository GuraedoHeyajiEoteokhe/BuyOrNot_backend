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
        name = "tbl_report",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"post_id", "reporter_id"})
        }
)
public class PostReport extends BaseEntity {

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportType type;

    @Column(length = 500)
    private String reason;

    public PostReport(Long postId, Long userId, ReportType type, String reason) {
        this.postId = postId;
        this.userId = userId;
        this.type = type;
        this.reason = reason;
    }
}
