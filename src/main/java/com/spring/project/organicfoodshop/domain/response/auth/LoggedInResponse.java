package com.spring.project.organicfoodshop.domain.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
public class LoggedInResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("user_info")
    private UserInfo userInfo;

    @Getter
    @Setter
    public static class UserInfo {
        private Long id;

        private String username;

        @JsonProperty("full_name")
        private String fullName;

        private String email;

        private String avatar;
    }
}
