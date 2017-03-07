package com.github.mykhalechko.contacts.repository;

import com.github.mykhalechko.contacts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByFullName(String fullName);
}
