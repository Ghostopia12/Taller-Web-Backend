package com.nur.usuarios.services.implementations;

import com.nur.usuarios.entities.User;
import com.nur.usuarios.repositories.UserRepository;
import com.nur.usuarios.services.interfaces.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User save(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return null;
        }
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAll(Integer page, Integer size, boolean enabled){
        return userRepository.findAll(enabled ? PageRequest.of(page, size): Pageable.unpaged());
    }

    @Override
    public User editUser(User user){
        if (userRepository.findById(user.getId()).isPresent()){
            return userRepository.save(user);
        }
        return null;
    }
}
