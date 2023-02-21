package com.zik.springsecurityDatabase.repo;

import com.zik.springsecurityDatabase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
