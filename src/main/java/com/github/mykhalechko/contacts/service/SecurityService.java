package com.github.mykhalechko.contacts.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
