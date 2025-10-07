package com.github.superz97.core.client;

import com.github.superz97.core.entity.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeSubscriptionRequest {

    private Long followeeId;

    private Long followerId;

    private SubscriptionType subscriptionType;

}
