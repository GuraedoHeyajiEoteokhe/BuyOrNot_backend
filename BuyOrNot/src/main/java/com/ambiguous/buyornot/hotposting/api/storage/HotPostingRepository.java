package com.ambiguous.buyornot.hotposting.api.storage;

import com.ambiguous.buyornot.hotposting.api.domain.HotPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HotPostingRepository extends JpaRepository<HotPosting, Long> {

    boolean existsByPostingId(Long postingId);



}
