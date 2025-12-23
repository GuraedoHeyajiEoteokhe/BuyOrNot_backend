package com.ambiguous.buyornot.posting.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.posting.api.controller.request.PostRequest;
import com.ambiguous.buyornot.posting.api.controller.response.PostResponse;
import com.ambiguous.buyornot.posting.api.domain.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/stocks/{stockId}/posts")
    @Operation(summary = "게시글 생성 API입니다.")
    public ApiResult<?> createPost(
            @PathVariable Long stockId,
            @RequestParam Long userId,
            @RequestBody PostRequest dto
    ) {
        String userNickname = "nickName";
        postService.createPost(stockId, userId, userNickname, dto);

        return ApiResult.success();
    }

    @GetMapping("/stocks/{stockId}/posts")
    @Operation(summary = "특정 종목의 게시글 목록 조회 API입니다.")
    public ApiResult<List<PostResponse>> userViewReport(
            @PathVariable Long stockId
    ) {
        return ApiResult.success(postService.getPostsByStockId(stockId));
    }

    @GetMapping("/posts/user")
    @Operation(summary = "유저별 게시글 목록 조회 API입니다.")
    public ApiResult<List<PostResponse>> getPosts(
            @RequestParam Long userId
    ) {
        return ApiResult.success(postService.getPostsByUserId(userId));
    }

    @GetMapping("/posts/title/{title}")
    @Operation(summary = "제목별 게시글 목록 조회 API입니다.")
    public ApiResult<List<PostResponse>> getPosts(
            @RequestParam String title
    ) {
        return ApiResult.success(postService.searchPostsByTitle(title));
    }
}
