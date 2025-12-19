package com.ambiguous.buyornot.chatting.api.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.annotation.Nullable;
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

    @Column(name = "post_id")
    Long postId;

    @Column(name = "user_id")
    Long userID;

    @Column(name = "message")
    String message;

    @Column(name = "report", nullable = false)
    int reports = 0;

    public void changeReports(Chatting chatting){
        this.reports+=1;
    }
}
