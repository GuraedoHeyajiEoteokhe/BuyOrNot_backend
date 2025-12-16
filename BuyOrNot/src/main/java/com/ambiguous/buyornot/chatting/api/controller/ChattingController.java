package com.ambiguous.buyornot.chatting.api.controller;

import com.ambiguous.buyornot.chatting.api.controller.request.ChattingRequest;
import com.ambiguous.buyornot.chatting.api.domain.Chatting;
import com.ambiguous.buyornot.chatting.api.domain.ChattingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChattingController {
    ChattingService chattingService;

    @Autowired
    public ChattingController(ChattingService chattingService) {
        this.chattingService = chattingService;
    }

    @PostMapping("/chatting")
    public boolean chatting(@RequestBody ChattingRequest chattingRequest){
        chattingService.saveMessage(chattingRequest);
        return true;

    }

    @DeleteMapping("/chatting/{id}")
    public boolean deleteChatting(@PathVariable Long id){
        chattingService.deleteMessage(id);
        return true;
    }

}
