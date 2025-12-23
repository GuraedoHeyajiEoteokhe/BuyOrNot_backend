package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.posting.api.domain.Post;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByStockIdOrderByCreatedAtDesc(Long stockId);
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Post> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String title);

    List<Post> findByCreatedAtAfter(LocalDateTime time);

    @Query("select p.likeCount from Post p where p.id = :id")
    Integer findLikeCount(@Param("id") Long id);
}
