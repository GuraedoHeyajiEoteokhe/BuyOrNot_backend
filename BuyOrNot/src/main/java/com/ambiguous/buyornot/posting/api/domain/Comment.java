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
public class Comment extends BaseEntity {

    @Column(nullable = false)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Comment parent;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 30)
    private String userNickname;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    public Comment(Long postId, Comment parent, Long userId, String userNickname, String content) {
        this.postId = postId;
        this.parent = parent;
        this.userId = userId;
        this.userNickname = userNickname;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void delete() {
        this.deleted = true;
    }
}
