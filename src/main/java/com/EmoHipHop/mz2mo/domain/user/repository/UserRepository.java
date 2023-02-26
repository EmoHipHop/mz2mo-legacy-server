package com.EmoHipHop.mz2mo.domain.user.repository;

import com.EmoHipHop.mz2mo.global.user.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
