package com.ambiguous.buyornot.posting.storage;

import com.ambiguous.buyornot.mypage.controller.mypageResponse.PostResponse;
import com.ambiguous.buyornot.posting.api.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    Post findTitleById(Long userid);
}
