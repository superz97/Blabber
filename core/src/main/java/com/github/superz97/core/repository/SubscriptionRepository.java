package com.github.superz97.core.repository;

import com.github.superz97.core.entity.Subscription;
import com.github.superz97.core.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s.follower FROM Subscription s WHERE s.followee.id = :followeeId")
    List<User> findFollowersByFolloweeId(@Param("followeeId") Long followeeId);

    boolean existsByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    void deleteByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    int deleteAllByFolloweeIdOrFollowerId(Long followeeId, Long followerId);

}
