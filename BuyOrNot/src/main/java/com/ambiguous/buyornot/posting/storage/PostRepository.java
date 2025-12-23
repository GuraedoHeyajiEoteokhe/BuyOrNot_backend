package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.posting.api.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByStockIdOrderByCreatedAtDesc(Long stockId);
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Post> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String title);
}
