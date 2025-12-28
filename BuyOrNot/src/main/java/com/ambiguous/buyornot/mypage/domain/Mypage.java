package com.ambiguous.buyornot.mypage.domain;

import com.ambiguous.buyornot.common.BaseEntity;
import com.ambiguous.buyornot.mypage.controller.mypageRequest.MypageRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Entity
@Table(name = "tbl_mypage")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mypage extends BaseEntity {
    Long userId;
    String likeStock;
    String ownStock;

    public void changeOwnStock(Long userId, String ownStock) {
        this.ownStock = ownStock;
    }

    public void changeLikeStock(Long userId, String likeStock) {
        this.likeStock = likeStock;
    }
}
