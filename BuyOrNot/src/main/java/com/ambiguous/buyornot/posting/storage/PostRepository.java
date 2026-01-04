package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.posting.api.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByStockIdOrderByCreatedAtDesc(Long stockId);
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Post> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String title);

    List<Post> findByCreatedAtAfter(LocalDateTime time);

    @Query("""
        select p
        from Post p
        where p.createdAt >= :oneHourAgo
          and p.likeCount >= :threshold
    """)
    List<Post> findHotCandidates(
            @Param("oneHourAgo") LocalDateTime oneHourAgo,
            @Param("threshold") long threshold
    );

    List<Post> findByIdIn(List<Long> ids);

    Post findTitleById(Long userid);
}
