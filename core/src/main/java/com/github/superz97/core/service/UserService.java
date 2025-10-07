package com.github.superz97.core.service;

import com.github.superz97.core.entity.SubscriptionType;
import com.github.superz97.core.entity.User;
import com.github.superz97.core.event.SubscriptionChangeApplicationEvent;
import com.github.superz97.core.exception.BlabberException;
import com.github.superz97.core.repository.SubscriptionRepository;
import com.github.superz97.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.internals.events.SubscriptionChangeEvent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SubscriptionRepository subscriptionRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

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
            @CacheEvict(value = "userById", key = "#userId"),
            @CacheEvict(value = "userSubscriptions", allEntries = true)
    })
    @Transactional
    public void deleteById(Long userId) {
        log.info("Delete user by id: {}", userId);
        int deleteCount = subscriptionRepository.deleteAllByFolloweeIdOrFollowerId(userId, userId);
        log.info("Deleted {} subscriptions", deleteCount);
        applicationEventPublisher.publishEvent(new SubscriptionChangeApplicationEvent(this, userId, userId, SubscriptionType.REMOVE));
        userRepository.deleteById(userId);
    }

}
