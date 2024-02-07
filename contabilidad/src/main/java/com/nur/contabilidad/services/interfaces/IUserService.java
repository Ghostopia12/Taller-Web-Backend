package com.nur.contabilidad.services.interfaces;

import com.sd.rainbowback.entities.User;
import org.springframework.data.domain.Page;


public interface IUserService {
    Page<User> findAll(Integer page, Integer size, boolean enabled);

    User editUser(User user);

    User findById(Long id);

    User save(User user);

}
