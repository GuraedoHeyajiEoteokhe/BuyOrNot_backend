package com.ambiguous.buyornot.mypage;

import com.ambiguous.buyornot.mypage.controller.mypageRequest.UpdateRequest;
import com.ambiguous.buyornot.user.entity.User;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MypageRepositoty extends JpaRepository {
    public User findById(Long id);
}
