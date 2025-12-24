package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public Post(Long stockId, Long userId, String userNickname, String title, String content) {
        this.stockId = stockId;
        this.userId = userId;
        this.userNickname = userNickname;
        this.title = title;
        this.content = content;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseReaction(ReactionType type) {
        if (type == ReactionType.LIKE) {
            this.likeCount++;
        } else {
            this.dislikeCount++;
        }
    }

    public void decreaseReaction(ReactionType type) {
        if (type == ReactionType.LIKE) {
            this.likeCount--;
        } else {
            this.dislikeCount--;
        }
    }
}
