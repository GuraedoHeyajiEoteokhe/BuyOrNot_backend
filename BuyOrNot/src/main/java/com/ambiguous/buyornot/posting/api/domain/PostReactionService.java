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

        Optional<PostReaction> optionalReaction = postReactionRepository.findByPostIdAndUserId(postId, userId);

        if (optionalReaction.isEmpty()) {
            postReactionRepository.save(new PostReaction(postId, userId, type));
            post.increaseReaction(type);
            return;
        }

        PostReaction reaction = optionalReaction.get();

        if (reaction.getType() == type) {
            throw new IllegalStateException("이미 해당 반응을 눌렀습니다.");
        }

        post.decreaseReaction(reaction.getType());
        reaction.changeType(type);
        post.increaseReaction(type);
    }

    public void cancelReaction(Long postId, Long userId) {

        PostReaction reaction = postReactionRepository
                .findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new IllegalArgumentException("취소할 반응이 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        post.decreaseReaction(reaction.getType());

        postReactionRepository.delete(reaction);
    }
}
