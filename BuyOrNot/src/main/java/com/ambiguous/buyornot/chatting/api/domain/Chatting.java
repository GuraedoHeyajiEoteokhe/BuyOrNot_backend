package com.ambiguous.buyornot.chatting.api.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Chatting extends BaseEntity {

    @Column
    Long userID;

    @Column
    Long boardID;

    @Column
    String message;

    @Column
    int reports;
}
