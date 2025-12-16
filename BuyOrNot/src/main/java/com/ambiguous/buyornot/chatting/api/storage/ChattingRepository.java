package com.ambiguous.buyornot.chatting.api.storage;

import com.ambiguous.buyornot.chatting.api.domain.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting,Long> {


    int findReportsById(Long id);
}
