package com.spring.project.organicfoodshop.domain.request.common.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeAccountPasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
