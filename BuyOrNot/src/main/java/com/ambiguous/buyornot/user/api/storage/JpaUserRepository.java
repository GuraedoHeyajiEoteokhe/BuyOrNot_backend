package com.ambiguous.buyornot.user.api.storage;

import com.ambiguous.buyornot.user.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends UserRepository, JpaRepository<User, Long>
{

}
