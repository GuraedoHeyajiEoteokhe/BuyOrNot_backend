package com.ambiguous.buyornot.hotposting.service;

import com.ambiguous.buyornot.hotposting.dto.record.HotPostingCreateCommand;
import com.ambiguous.buyornot.hotposting.entity.HotPosting;
import com.ambiguous.buyornot.hotposting.repository.HotPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HotPostingService {

    private HotPostingRepository hotPostingRepository;

    @Transactional
    public void create(HotPostingCreateCommand command){

        if(hotPostingRepository.existsByPostingId(command.postingId())){
            throw new IllegalStateException("이미 핫게시글로 등록된 게시글입니다.");
        }

        LocalDateTime now = LocalDateTime.now();

        HotPosting hotPosting = new HotPosting(
                command.postingId(),
                command.writerId(),
                command.symbol(),
                command.writeAt(),
                now,
                command.writeAt().plusHours(72)
        );

        hotPostingRepository.save(hotPosting);

    }
}
