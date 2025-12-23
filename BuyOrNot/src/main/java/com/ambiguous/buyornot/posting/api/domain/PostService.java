package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.api.controller.request.PostRequest;
import com.ambiguous.buyornot.posting.api.controller.response.PostDetailResponse;
import com.ambiguous.buyornot.posting.api.controller.response.PostListResponse;
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
    public List<PostListResponse> getPostsByStockId(Long stockId) {

        return postRepository.findByStockIdOrderByCreatedAtDesc(stockId)
                .stream()
                .map(PostListResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PostListResponse> getPostsByUserId(Long userId) {

        return postRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(PostListResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PostListResponse> searchPostsByTitle(String keyword) {

        return postRepository.findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(keyword)
                .stream()
                .map(PostListResponse::from)
                .toList();
    }

    public PostDetailResponse getPostDetail(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        post.increaseViewCount();

        return PostDetailResponse.from(post);
    }
}
