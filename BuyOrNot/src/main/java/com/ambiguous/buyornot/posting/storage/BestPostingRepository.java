package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.posting.api.controller.response.BestPostSummaryResponse;
import com.ambiguous.buyornot.posting.api.domain.BestPosting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


public interface BestPostingRepository extends JpaRepository<BestPosting, Long> {

    @Query("""
        select new com.ambiguous.buyornot.posting.api.controller.response.BestPostSummaryResponse(
            p.id,
            p.stockId,
            p.userId,
            p.userNickname,
            p.title,
            p.viewCount,
            p.likeCount,
            p.dislikeCount
        )
        from BestPosting b
        join Post p on p.id = b.postingId
        where p.deleted = false
        order by b.createdAt desc
    """)
    Page<BestPostSummaryResponse> findBestPosts(Pageable pageable);

    @Query("""
        select new com.ambiguous.buyornot.posting.api.controller.response.BestPostSummaryResponse(
            p.id,
            p.stockId,
            p.userId,
            p.userNickname,
            p.title,
            p.viewCount,
            p.likeCount,
            p.dislikeCount
        )
        from BestPosting b
        join Post p on p.id = b.postingId
        where p.deleted = false
          and p.stockId = :stockId
        order by b.createdAt desc
    """)
    Page<BestPostSummaryResponse> findBestPostsByStockId(@Param("stockId") Long stockId, Pageable pageable);

    boolean existsByPostingId(Long postingId);

}
