package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.api.controller.request.CreatePostDto;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

private final PostRepository postRepository;

    @Transactional
    public void createPost(Long stockId, Long userId, String nickname, CreatePostDto dto) {

        Post post = dto.toEntity(stockId, userId, nickname);
        postRepository.save(post);
    }

}
