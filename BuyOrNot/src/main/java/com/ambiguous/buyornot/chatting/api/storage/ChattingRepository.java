package com.ambiguous.buyornot.chatting.api.storage;

import com.ambiguous.buyornot.chatting.api.domain.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChattingRepository extends JpaRepository<Chatting,Long> {


    int findReportsById(Long id);
    List<Chatting> findAll();

    Optional<Chatting> findById(Long id);

}
