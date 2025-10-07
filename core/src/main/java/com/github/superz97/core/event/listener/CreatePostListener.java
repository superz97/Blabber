package com.github.superz97.core.event.listener;

import com.github.superz97.core.event.CreatePostApplicationEvent;
import com.github.superz97.core.event.CreatePostKafkaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePostListener {

    @Value("${app.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, CreatePostKafkaEvent> createPostKafkaTemplate;

    @EventListener
    public void onEvent(CreatePostApplicationEvent event) {
        log.info("Get event for create post {}", event);

        createPostKafkaTemplate.send(
                topic,
                new CreatePostKafkaEvent(event.getPostId(), event.getAuthorId(), event.getUsername())
        );
    }

}
