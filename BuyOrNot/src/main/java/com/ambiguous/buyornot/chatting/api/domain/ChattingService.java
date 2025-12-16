package com.ambiguous.buyornot.chatting.api.domain;

import com.ambiguous.buyornot.chatting.api.controller.request.ChattingRequest;
import com.ambiguous.buyornot.chatting.api.storage.ChattingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChattingService {
    ChattingRepository chattingRepository;

    public void saveMessage(ChattingRequest chattingRequest) {
        chattingRepository.save(ChattingRequest);

    }

    public void deleteMessage(Long id) {
        int messageReport = chattingRepository.findReportsById(id);
        if(messageReport >= 10){
            chattingRepository.deleteById(id);
        }
    }
}
