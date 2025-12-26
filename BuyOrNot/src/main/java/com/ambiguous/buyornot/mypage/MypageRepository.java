package com.ambiguous.buyornot.mypage.storage;

import com.ambiguous.buyornot.mypage.domain.Mypage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MypageRepository extends JpaRepository<Mypage, Long> {

//    boolean existsByNickname(String nickname);

    Mypage findByUserId(Long userId);

    List<Mypage> findLikeStockById(Long id);

    List<Mypage> findOwnStockById(Long userid);
}