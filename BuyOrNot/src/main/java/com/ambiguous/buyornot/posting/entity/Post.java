package com.ambiguous.buyornot.posting.entity;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Column(nullable = false)
    private Long stockId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false, length = 200)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private long viewCount = 0;

    @Column(nullable = false)
    private long likeCount = 0;

    @Column(nullable = false)
    private long dislikeCount = 0;
}
