package com.ambiguous.buyornot.mypage.storage;

import com.ambiguous.buyornot.mypage.domain.Mypage;
import com.ambiguous.buyornot.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MypageRepositoty extends JpaRepository<Mypage, Long> {

//    boolean existsByNickname(String nickname);

    Mypage findByUserId(Long userId);
}
