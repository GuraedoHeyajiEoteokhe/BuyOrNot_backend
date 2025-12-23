package com.ambiguous.buyornot.hotposting.api.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name="tbl_hotposting")
@Entity
public class HotPosting extends BaseEntity {

    @Column(name = "posting_id", nullable = false, unique = true)
    private Long postingId; // 게시글 아이디 - 게시글 테이블

    @Column(name = "writer_id", nullable = false)
    private Long writerId;  // 게시글 작성자 아이디 - 게시글 테이블(사용자 테이블)

    @Column(name = "symbol", nullable = false, length = 20)
    private String symbol;  // 종목 종류

    @Column(name = "write_at", nullable = false)
    private LocalDateTime writeAt;  // 게시글 작성 시각 (post.writeAt 같은 의미)

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt; // 핫 등록 시각 (정렬 기준)

    @Column(name = "expire_at", nullable = false)
    private LocalDateTime expireAt; // 핫 만료 시각 (registeredAt + 72h)

    public HotPosting(Long postingId, Long writerId, String symbol,
                      LocalDateTime writeAt,
                      LocalDateTime registeredAt,
                      LocalDateTime expireAt) {
        this.postingId = postingId;
        this.writerId = writerId;
        this.symbol = symbol;
        this.writeAt = writeAt;
        this.registeredAt = registeredAt;
        this.expireAt = expireAt;
    }
}
