package com.spring.project.organicfoodshop.domain.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IntrospectedResponse {

    @JsonProperty("user_info")
    private UserInfo userInfo;

    @Getter
    @Setter
    public static class UserInfo extends LoggedInResponse.UserInfo {
        private String phone;

        private Integer age;

        private String gender;

        private String address;
    }
}
