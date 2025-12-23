package com.ambiguous.buyornot.mypage.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_mypage")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mypage extends BaseEntity {
    Long userId;
    String likeStock;
    String ownStock;
}
