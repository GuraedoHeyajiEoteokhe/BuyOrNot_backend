package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.posting.api.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByStockIdOrderByCreatedAtDesc(Long stockId);
}
