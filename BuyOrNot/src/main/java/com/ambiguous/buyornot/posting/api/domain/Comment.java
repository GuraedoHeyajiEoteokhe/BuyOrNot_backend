package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_comment")
public class Comment extends BaseEntity {

    @Column(nullable = false)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Comment parent;

    private Long userId;

    @Column(nullable = false, length = 30)
    private String userNickname;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean pinned = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentDeleteType deleteReason = CommentDeleteType.NONE;

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

    public void pin() {
        this.pinned = true;
    }

    public void unpin() {
        this.pinned = false;
    }

    public boolean isDeleted() {
        return deleteReason != CommentDeleteType.NONE;
    }

    public void softDeleteByUser() {
        this.deleteReason = CommentDeleteType.USER;
        this.pinned = false;
    }

    public void softDeleteByReport() {
        this.deleteReason = CommentDeleteType.REPORT;
        this.pinned = false;
    }
}
