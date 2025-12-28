package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "tbl_reaction",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"post_id", "user_id"})
        }
)
public class PostReaction extends BaseEntity {

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionType type;

    public PostReaction(Long postId, Long userId, ReactionType type) {
        this.postId = postId;
        this.userId = userId;
        this.type = type;
    }

    public void changeType(ReactionType newType) {
        this.type = newType;
    }
}
