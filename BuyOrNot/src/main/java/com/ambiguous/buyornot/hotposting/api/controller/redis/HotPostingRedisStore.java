package com.ambiguous.buyornot.hotposting.api.controller.redis;

import com.ambiguous.buyornot.common.support.key.AmbiguousKey;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@RequiredArgsConstructor
public class HotPostingRedisStore {

    private final RedisTemplate<String, Object> redisTemplate;


    public void add(Long postingId, Long stockId, LocalDateTime registeredAt){
        // 키 생성
        String key = AmbiguousKey.hotPostingTop30(stockId);
        /*
        * ZSET: 값을 넣을 때 점수를 매겨 점수를 기준으로 자동 정렬해줌
        * value = postingId
        * score = 핫 등록 시간*/
        double score = registeredAt.toEpochSecond(ZoneOffset.UTC);

        // key라는 zset에 postingId(value)를 점수 기준으로 정렬되도록 저장
        redisTemplate.opsForZSet().add(key, postingId.toString(), score);

        // 만약 30개 넘을 경우 잘라냄
        trimToTop30(key);

    }

    // 핫포스팅 스케줄러에서 게시글이 존재하는지 확인하는 메서드에서 사용 예정
    public void remove(Long postingId, Long stockId){
        String key = AmbiguousKey.hotPostingTop30(stockId);
        redisTemplate.opsForZSet().remove(key, postingId.toString());

    }

    // 현재 이 post가 redis top30에 존재하는지 검증하는 메서드
    public boolean isInTop30(Long stockId, Long postingId){
        String key = AmbiguousKey.hotPostingTop30(stockId);
        Double score = redisTemplate.opsForZSet()
                .score(key, postingId.toString());

        return score != null;
    }

    private void trimToTop30(String key) {
        Long size = redisTemplate.opsForZSet().zCard(key);
        if(size != null && size > 30){
            long removeCount = size - 30;
            // 오래된 것부터 제거(낮은 score부터)
            redisTemplate.opsForZSet().removeRange(key,0,removeCount - 1);
        }
    }
}
