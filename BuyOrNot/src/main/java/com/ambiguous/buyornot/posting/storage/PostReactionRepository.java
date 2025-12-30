package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.posting.api.domain.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {

    Optional<PostReaction> findByPostIdAndUserId(Long postId, Long userId);

    void deleteByPostId(Long postId);
}

