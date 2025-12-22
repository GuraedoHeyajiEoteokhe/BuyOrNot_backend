package com.ambiguous.buyornot.hotposting.controller;

import com.ambiguous.buyornot.hotposting.dto.record.HotPostingCreateCommand;
import com.ambiguous.buyornot.hotposting.dto.request.HotPostingCreateRequest;
import com.ambiguous.buyornot.hotposting.entity.HotPosting;
import com.ambiguous.buyornot.hotposting.service.HotPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/hot-posting")
public class HotPostingAdminController {

    private final HotPostingService hotPostingService;

    // Spring Security 적용 후 ADMIN 권한만 접근 가능하도록 제한 예정
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody HotPostingCreateRequest request){
        // 추후 role이 admin일때만
        hotPostingService.create(
                new HotPostingCreateCommand(
                        request.getPostingId(),
                        request.getWriterId(),
                        request.getSymbol(),
                        request.getWriteAt()
                )
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}