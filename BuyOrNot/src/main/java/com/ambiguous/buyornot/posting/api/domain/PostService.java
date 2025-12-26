package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.api.controller.request.PostSearchRequest;
import com.ambiguous.buyornot.posting.api.controller.request.UpdatePostRequest;
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

    public void save(Post post) {
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
    public List<PostListResponse> searchPosts(PostSearchRequest request) {

        if (request.getUserId() != null) {
            return getPostsByUserId(request.getUserId());
        }

        if (request.getTitle() != null) {
            return searchPostsByTitle(request.getTitle());
        }

        return List.of();
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

    @Transactional
    public void updatePost(Long postId, UpdatePostRequest request) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (!post.getUserId().equals(request.userId())) {
            throw new IllegalStateException("게시글 수정 권한이 없습니다.");
        }

        post.update(request.title(), request.content());
    }

    @Transactional
    public void deletePost(Long postId, Long userId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (!post.getUserId().equals(userId)) {
            throw new IllegalStateException("게시글 삭제 권한이 없습니다.");
        }

        postRepository.delete(post);
    }
}
