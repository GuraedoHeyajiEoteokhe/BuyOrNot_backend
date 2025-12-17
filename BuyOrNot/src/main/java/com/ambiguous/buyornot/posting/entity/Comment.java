package com.ambiguous.buyornot.posting.entity;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Column(nullable = false)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parentId")
    private Comment parent; // null이면 댓글, 있으면 대댓글

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 30)
    private String userNickname;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean deleted = false;
}
