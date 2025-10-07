package com.github.superz97.core.event.listener;

import com.github.superz97.core.client.SubscriptionClient;
import com.github.superz97.core.event.SubscriptionChangeApplicationEvent;
import com.github.superz97.core.exception.BlabberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubscriptionChangeListener {

    private final SubscriptionClient client;

    @EventListener
    public void onEvent(SubscriptionChangeApplicationEvent event) {
        log.info("Get event for subscription change: {}", event);
        switch (event.getSubscriptionType()) {
            case SUBSCRIBE -> client.subscribe(event.getFolloweeId(), event.getFollowerId());
            case UNSUBSCRIBE -> client.unsubscribe(event.getFolloweeId(), event.getFollowerId());
            case REMOVE -> client.deleteSubscription(event.getFolloweeId());
            default -> throw new BlabberException("Type not found: " + event.getSubscriptionType());
        }
    }

}
