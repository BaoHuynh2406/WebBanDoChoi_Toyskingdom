package com.mts.toyskingdom.repository;

import com.mts.toyskingdom.data.model.UserM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserM, Long> {
    UserM findByEmail(String email);
}
