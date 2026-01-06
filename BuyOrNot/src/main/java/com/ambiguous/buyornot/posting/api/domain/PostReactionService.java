package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.storage.BestPostingRepository;
import com.ambiguous.buyornot.posting.storage.PostReactionRepository;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostReactionService {
    private static final long BEST_THRESHOLD = 100;

    private final PostRepository postRepository;
    private final PostReactionRepository postReactionRepository;
    private final BestPostingRepository bestPostingRepository;

    public void react(Long postId, Long userId, ReactionType type) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Optional<PostReaction> optional = postReactionRepository
                .findByPostIdAndUserId(postId, userId);

        // 반응 생성
        if (optional.isEmpty()) {
            postReactionRepository.save(new PostReaction(postId, userId, type));
            post.increaseReaction(type);

            if (type == ReactionType.LIKE) {
                tryRegisterBestPost(postId, post.getStockId(), post.getLikeCount());
            }

            return;
        }

        PostReaction reaction = optional.get();

        // 반응 취소
        if (reaction.getType() == type) {
            post.decreaseReaction(type);
            postReactionRepository.delete(reaction);
            return;
        }
        ReactionType prev = reaction.getType();

        // 반응 변경
        post.decreaseReaction(prev);
        reaction.changeType(type);
        post.increaseReaction(type);

        if (prev != ReactionType.LIKE && type == ReactionType.LIKE) {
            tryRegisterBestPost(postId, post.getStockId(), post.getLikeCount());
        }
    }

    // 베스트 포스트 등록
    private void tryRegisterBestPost(Long postId, Long stockId, long likeCount){
        if (likeCount < BEST_THRESHOLD) return;

        try {
            bestPostingRepository.save(BestPosting.builder()
                    .postingId(postId)
                    .stockId(stockId)
                    .build());
        } catch (DataIntegrityViolationException e) {
            log.debug("BestPosting already exists. postId={}", postId);
        }
    }
}
