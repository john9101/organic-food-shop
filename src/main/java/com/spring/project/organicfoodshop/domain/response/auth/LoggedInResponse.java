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

    @JsonProperty("metadata")
    private Metadata metadata;

    @Getter
    @Setter
    public static class Metadata {
        private Long id;

        @JsonProperty("full_name")
        private String fullName;

        private String email;
    }
}
