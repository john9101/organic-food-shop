package com.spring.project.organicfoodshop.domain.request.common.cart;

import com.spring.project.organicfoodshop.domain.User;
import lombok.Builder;

@Builder
public class InitializeCartRequest {
    private User user;
}
