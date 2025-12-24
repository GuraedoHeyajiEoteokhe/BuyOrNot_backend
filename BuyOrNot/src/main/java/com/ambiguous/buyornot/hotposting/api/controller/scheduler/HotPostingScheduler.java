package com.ambiguous.buyornot.hotposting.api.controller.scheduler;

import com.ambiguous.buyornot.hotposting.api.domain.HotPosting;
import com.ambiguous.buyornot.hotposting.api.domain.HotPostingService;
import com.ambiguous.buyornot.hotposting.api.storage.HotPostingRepository;
import com.ambiguous.buyornot.posting.api.domain.Post;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class HotPostingScheduler {

    private final PostRepository postRepository;
    private final HotPostingService hotPostingService;
    private final HotPostingRepository hotPostingRepository;

    @Scheduled(fixedRate = 60000)
    public void hotPostingRegister(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourAgo = now.minusHours(1);

        for(Post p : postRepository.findByCreatedAtAfter(oneHourAgo)){
            int like = postRepository.findLikeCount(p.getId());

            if(like >= 10){
                hotPostingService.registerFromPost(p);
            }
        }

    }

}
