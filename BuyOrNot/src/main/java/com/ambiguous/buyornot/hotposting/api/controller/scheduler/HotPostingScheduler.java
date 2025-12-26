package com.ambiguous.buyornot.hotposting.api.controller.scheduler;

import com.ambiguous.buyornot.hotposting.api.controller.redis.HotPostingRedisStore;
import com.ambiguous.buyornot.hotposting.api.domain.HotPosting;
import com.ambiguous.buyornot.hotposting.api.domain.HotPostingService;
import com.ambiguous.buyornot.hotposting.api.storage.HotPostingRepository;
import com.ambiguous.buyornot.posting.api.domain.Post;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HotPostingScheduler {

    private final HotPostingService hotPostingService;


    @Scheduled(fixedRate = 60000)
    public void hotPostingRegister(){
        hotPostingService.autoRegisterHotPostings();

    }

    @Scheduled(fixedRate = 5*60*1000)
    public void cleanupExpiredFromRedis(){

        hotPostingService.cleanupExpiredFromRedis();

    }

}
