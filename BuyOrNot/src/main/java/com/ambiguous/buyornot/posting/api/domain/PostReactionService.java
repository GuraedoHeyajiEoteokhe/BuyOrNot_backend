package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.storage.PostReactionRepository;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostReactionService {

    private final PostRepository postRepository;
    private final PostReactionRepository postReactionRepository;

    public void react(Long postId, Long userId, ReactionType type) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Optional<PostReaction> optional = postReactionRepository
                .findByPostIdAndUserId(postId, userId);

        // 반응 생성
        if (optional.isEmpty()) {
            postReactionRepository.save(new PostReaction(postId, userId, type));
            post.increaseReaction(type);
            return;
        }

        PostReaction reaction = optional.get();

        // 반응 취소
        if (reaction.getType() == type) {
            post.decreaseReaction(type);
            postReactionRepository.delete(reaction);
            return;
        }

        // 반응 변경
        post.decreaseReaction(reaction.getType());
        reaction.changeType(type);
        post.increaseReaction(type);
    }
}
