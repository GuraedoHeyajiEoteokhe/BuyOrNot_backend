package com.ambiguous.buyornot.chatting.api.domain;

import com.ambiguous.buyornot.chatting.api.controller.request.ChattingRequest;
import com.ambiguous.buyornot.chatting.api.controller.response.ChattingResponse;
import com.ambiguous.buyornot.chatting.storage.ChattingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChattingService {
    ChattingRepository chattingRepository;

    public void saveMessage(ChattingRequest chattingRequest) {
        Chatting chatting = new Chatting(
                chattingRequest.postId(),
                chattingRequest.userId(),
                chattingRequest.Message(),
                0
        );
        chattingRepository.save(chatting);
    }

    public void deleteMessage(Long id) {
        int messageReport = chattingRepository.findReportsById(id);
        if(messageReport >= 10){
            chattingRepository.deleteById(id);
        }
    }

    public List<Chatting> findAll() {
        return chattingRepository.findAll().stream().toList();

    }

    public void updateReport(Long id) {
        Chatting chatting = chattingRepository.findChattingById();
        chatting.changeReports(chatting);
    }
}
