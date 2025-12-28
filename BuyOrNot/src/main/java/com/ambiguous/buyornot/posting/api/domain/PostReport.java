package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_report")
public class PostReport extends BaseEntity {

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long reporterId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportType type;

    @Column(length = 500)
    private String reason;
}
