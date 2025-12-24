package com.ambiguous.buyornot.hotposting.api.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name="tbl_bestposting")
public class BestPosting extends BaseEntity {

    @Column(name = "posting_id", nullable = false, unique = true)
    private Long postingId; // 게시글 아이디

    @Column(name = "writer_id", nullable = false)
    private Long writerId;  // 작성자 아이디

    @Column(name = "symbol", nullable = false, length = 20)
    private String symbol;  // 종목 심볼

    @Column(name = "write_at", nullable = false)
    private LocalDateTime writeAt;  // 게시글 작성 시각 (정렬 기준)

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt; // 베스트 게시글 등록 시각
}
