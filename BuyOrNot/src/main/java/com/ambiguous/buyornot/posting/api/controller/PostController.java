package com.ambiguous.buyornot.posting.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.posting.api.controller.request.PostRequest;
import com.ambiguous.buyornot.posting.api.controller.request.UpdatePostRequest;
import com.ambiguous.buyornot.posting.api.controller.response.PostDetailResponse;
import com.ambiguous.buyornot.posting.api.controller.response.PostListResponse;
import com.ambiguous.buyornot.posting.api.domain.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/stocks/{stockId}")
    @Operation(summary = "게시글 생성 API입니다.")
    public ApiResult<?> createPost(
            @PathVariable Long stockId,
            @RequestParam Long userId,
            @RequestBody PostRequest dto
    ) {
        String nickname = "nickName";
        postService.createPost(stockId, userId, nickname, dto);

        return ApiResult.success();
    }

    @GetMapping("/stocks/{stockId}")
    @Operation(summary = "특정 종목의 게시글 목록 조회 API입니다.")
    public ApiResult<List<PostListResponse>> userViewReport(
            @PathVariable Long stockId
    ) {
        return ApiResult.success(postService.getPostsByStockId(stockId));
    }

    @GetMapping("/user")
    @Operation(summary = "유저별 게시글 목록 조회 API입니다.")
    public ApiResult<List<PostListResponse>> getPosts(
            @RequestParam Long userId
    ) {
        return ApiResult.success(postService.getPostsByUserId(userId));
    }

    @GetMapping("/title/{title}")
    @Operation(summary = "제목별 게시글 목록 조회 API입니다.")
    public ApiResult<List<PostListResponse>> getPosts(
            @RequestParam String title
    ) {
        return ApiResult.success(postService.searchPostsByTitle(title));
    }

    @GetMapping("/{postId}")
    @Operation(summary = "게시글 상세 조회 API입니다.")
    public ApiResult<PostDetailResponse> getPostDetail(
            @PathVariable Long postId
    ) {
        return ApiResult.success(postService.getPostDetail(postId));
    }

    @PutMapping("/{postId}")
    @Operation(summary = "게시글 수정 API입니다.")
    public ApiResult<?> updatePost(
            @PathVariable Long postId,
            @RequestParam Long userId,
            @RequestBody UpdatePostRequest request
    ) {
        postService.updatePost(postId, userId, request);
        return ApiResult.success();
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 삭제 API입니다.")
    public ApiResult<?> deletePost(
            @PathVariable Long postId,
            @RequestParam Long userId
    ) {
        postService.deletePost(postId, userId);
        return ApiResult.success();
    }
}