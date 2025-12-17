package com.ambiguous.buyornot.chatting.api.controller;

import com.ambiguous.buyornot.chatting.api.controller.request.ChattingRequest;
import com.ambiguous.buyornot.chatting.api.domain.Chatting;
import com.ambiguous.buyornot.chatting.api.domain.ChattingService;
import com.ambiguous.buyornot.chatting.api.support.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChattingController {
    ChattingService chattingService;

    @Autowired
    public ChattingController(ChattingService chattingService) {
        this.chattingService = chattingService;
    }

    @PostMapping("/chatting")
    public ApiResult<?> chatting(@RequestBody ChattingRequest chattingRequest){
        chattingService.saveMessage(chattingRequest);
        return ApiResult.success();

    }

    @DeleteMapping("/chatting/{id}")
    public boolean deleteChatting(@PathVariable Long id){
        chattingService.deleteMessage(id);
        return true;
    }

    @GetMapping("/chatting")
    public ApiResult<List<Chatting>> getChatting(){
        return ApiResult.success(chattingService.findAll());
    }

    @PostMapping("/chatting/{id}")
    public ApiResult<?> updateReports(@PathVariable Long id){
        return ApiResult.success();
    }
}
