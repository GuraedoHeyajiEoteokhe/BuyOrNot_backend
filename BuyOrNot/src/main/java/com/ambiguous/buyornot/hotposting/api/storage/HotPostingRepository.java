package com.ambiguous.buyornot.hotposting.api.storage;

import com.ambiguous.buyornot.hotposting.api.domain.HotPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HotPostingRepository extends JpaRepository<HotPosting, Long> {

    boolean existsByPostingId(Long postingId);

    // 만료됐고 아직 Redis에 남아있을 가능성이 있는 대상만 뽑기
    // 추후 데이터가 많아질 경우 page로 수정
    List<HotPosting> findByExpireAtBeforeAndRedisSyncedTrue(LocalDateTime now);


}
