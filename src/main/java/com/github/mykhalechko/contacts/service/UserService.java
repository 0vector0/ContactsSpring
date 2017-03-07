package com.github.mykhalechko.contacts.service;


import com.github.mykhalechko.contacts.entity.User;

public interface UserService {
    void edit(User user);

    void save(User user);

    User findByUsername(String username);

    User findByFullName(String fullName);

    User findById(Long id);

    public User getAuthenticationUser();


}
