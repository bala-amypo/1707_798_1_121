com.example.demo.entity
com.example.demo.repository
com.example.demo.service
com.example.demo.service.impl
package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    User register(User user);

    User findByEmail(String email);
}
