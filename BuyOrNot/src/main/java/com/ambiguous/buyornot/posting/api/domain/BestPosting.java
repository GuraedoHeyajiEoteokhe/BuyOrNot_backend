package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
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

    @Column(name = "stock_id", nullable = false)
    private Long stock_id;  // 종목 심볼

    @Builder
    public BestPosting(Long postingId, Long stockId) {
        this.postingId = postingId;
        this.stock_id = stockId;
    }
}
