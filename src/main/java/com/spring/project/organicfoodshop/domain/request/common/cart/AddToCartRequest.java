package com.spring.project.organicfoodshop.domain.request.common.cart;

import com.spring.project.organicfoodshop.util.annotation.AdvanceRequestBodyValidation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AdvanceRequestBodyValidation
public class AddToCartRequest {
    @NotNull(message = "Mã sản phẩm không bỏ trống")
    private Long productId;

    @NotNull(message = "Số lượng thêm vào giỏ hàng không được bỏ trống")
    @Min(value = 1, message = "Số lượng thêm vào giỏ hàng phải ít nhất là {value}")
    private Integer quantity;
}
