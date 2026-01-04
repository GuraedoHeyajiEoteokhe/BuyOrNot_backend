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

    @Column(name = "stock_id", nullable = false)
    private Long stockId;  // 종목 종류

    @Column(name = "write_at", nullable = false)
    private LocalDateTime writeAt;  // 게시글 작성 시각 (post.writeAt 같은 의미)

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt; // 핫 등록 시각 (정렬 기준)

    @Column(name = "expire_at", nullable = false)
    private LocalDateTime expireAt; // 핫 만료 시각 (registeredAt + 72h)

    // Redis 반영 성공 여부 (동기화 상태)
    @Column(name = "redis_synced", nullable = false)
    private boolean redisSynced;

    // 마지막 Redis 반영 성공 시각
    @Column(name = "redis_synced_at")
    private LocalDateTime redisSyncedAt;

    public HotPosting(Long postingId,Long stockId,
                      LocalDateTime writeAt,
                      LocalDateTime registeredAt,
                      LocalDateTime expireAt) {
        this.postingId = postingId;
        this.stockId = stockId;
        this.writeAt = writeAt;
        this.registeredAt = registeredAt;
        this.expireAt = expireAt;

        // 기본값: 아직 Redis 반영 안 됨
        this.redisSynced = false;
        this.redisSyncedAt = null;
    }

    /** Redis 반영 성공 시 호출 */
    public void markRedisSynced(LocalDateTime syncedAt) {
        this.redisSynced = true;
        this.redisSyncedAt = syncedAt;
    }

    /** (선택) Redis 반영 실패/초기화 시 */
    public void markRedisUnsynced() {
        this.redisSynced = false;
        this.redisSyncedAt = null;
    }
}
