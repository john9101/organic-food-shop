package com.spring.project.organicfoodshop.domain.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("meta_info")
    private MetaInfo metaAuthInfo;

    @Setter
    @Getter
    @Builder
    public static class MetaInfo{
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String username;
    }

}
