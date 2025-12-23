package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.api.controller.request.PostRequest;
import com.ambiguous.buyornot.posting.api.controller.response.PostResponse;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

private final PostRepository postRepository;

    public void createPost(Long stockId, Long userId, String nickname, PostRequest dto) {

        Post post = dto.toEntity(stockId, userId, nickname);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByStockId(Long stockId) {

        return postRepository.findByStockIdOrderByCreatedAtDesc(stockId)
                .stream()
                .map(PostResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUserId(Long userId) {

        return postRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(PostResponse::from)
                .toList();
    }
}
