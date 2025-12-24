package com.ambiguous.buyornot.user.api.storage;

import com.ambiguous.buyornot.user.api.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    void deleteByUserId(String userId);

}
