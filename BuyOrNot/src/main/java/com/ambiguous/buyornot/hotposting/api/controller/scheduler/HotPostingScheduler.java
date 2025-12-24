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

    private final PostRepository postRepository;
    private final HotPostingService hotPostingService;
    private final HotPostingRepository hotPostingRepository;
    private final HotPostingRedisStore hotPostingRedisStore;

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

    @Scheduled(fixedRate = 5*60*1000)
    public void cleanupExpiredFromRedis(){
        LocalDateTime now = LocalDateTime.now();
        // 만료되었지만 Redis에 올라간 상태인 핫포스팅 찾기
        List<HotPosting> expired = hotPostingRepository.findByExpireAtBeforeAndRedisSyncedTrue(now);

        for (HotPosting hotPosting : expired){
            // Redis에서 제거
            hotPostingRedisStore.remove(hotPosting.getPostingId(), hotPosting.getStockId());

            // 핫포스팅 DB에 Redis에서 삭제되었다는 표시
            hotPosting.markRedisUnsynced();
        }

    }

}
