package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.posting.api.domain.BestPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestPostingRepository extends JpaRepository<BestPosting, Long> {
}
