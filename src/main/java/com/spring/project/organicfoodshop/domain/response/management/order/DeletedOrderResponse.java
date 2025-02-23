package com.spring.project.organicfoodshop.domain.response.management.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class DeletedOrderResponse {
    private UUID id;
}
