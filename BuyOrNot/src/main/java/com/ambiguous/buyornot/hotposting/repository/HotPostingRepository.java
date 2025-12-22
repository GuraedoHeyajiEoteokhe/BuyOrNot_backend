package com.ambiguous.buyornot.hotposting.repository;

import com.ambiguous.buyornot.hotposting.entity.HotPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotPostingRepository extends JpaRepository<HotPosting, Long> {

    boolean existsByPostingId(Long postingId);

    void deleteByPostingId(Long postingId);

    List<HotPosting> findTop30ByOrderByRegisteredAtDesc();
}
