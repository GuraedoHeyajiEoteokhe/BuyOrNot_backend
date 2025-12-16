package com.ambiguous.buyornot.chatting.api.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Chatting extends BaseEntity {

    @Column
    Long boardID;

    @Column
    Long userID;

    @Column
    String message;

    @Column(nullable = false)
    int reports = 0;

    public void changeReports(Chatting chatting){
        this.reports+=1;
    }
}
