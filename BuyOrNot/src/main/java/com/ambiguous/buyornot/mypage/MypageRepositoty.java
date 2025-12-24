package com.ambiguous.buyornot.mypage;

import com.ambiguous.buyornot.user.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MypageRepositoty extends JpaRepository<User, Long> {
    public Optional<User> findById(Long id);
}
