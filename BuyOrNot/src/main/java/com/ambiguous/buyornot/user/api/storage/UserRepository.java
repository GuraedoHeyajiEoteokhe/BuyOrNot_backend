package com.ambiguous.buyornot.user.api.storage;

import com.ambiguous.buyornot.user.api.domain.User;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUserId(String userId);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndUserName(String email, String userName);

    User save(User user);

    void delete(User user);
}
