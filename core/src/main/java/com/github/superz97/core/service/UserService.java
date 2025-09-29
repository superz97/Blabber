package com.github.superz97.core.service;

import com.github.superz97.core.entity.User;
import com.github.superz97.core.exception.BlabberException;
import com.github.superz97.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Cacheable(value = "userById", key = "#userId")
    public User findById(Long userId) {
        log.info("Get user by id: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new BlabberException("User not found"));
    }

    public User create(User user) {
        log.info("Save user with username: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "userById", key = "#userId")
    })
    @Transactional
    public void deleteById(Long userId) {
        log.info("Delete user by id: {}", userId);
        userRepository.deleteById(userId);
    }

}
