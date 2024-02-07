package com.nur.usuarios.services.interfaces;

import com.nur.usuarios.entities.User;
import org.springframework.data.domain.Page;


public interface IUserService {
    Page<User> findAll(Integer page, Integer size, boolean enabled);

    User editUser(User user);

    User findById(Long id);

    User save(User user);

}
