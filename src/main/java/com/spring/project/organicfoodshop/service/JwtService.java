package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.response.auth.LoggedInResponse;
import com.spring.project.organicfoodshop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;

    @Value("${security.authentication.jwt.access-token-validity-in-seconds}")
    private long accessTokenValidityInSeconds;

    @Value("${security.authentication.jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenValidityInSeconds;

    public String createToken(Authentication authentication, LoggedInResponse.UserInfo userInfo, boolean refreshToken) {
        Instant issuedAt = Instant.now();
        Instant expiresAt;
        if (!refreshToken) {
            expiresAt = issuedAt.plus(accessTokenValidityInSeconds, ChronoUnit.SECONDS);
        }else {
            expiresAt = issuedAt.plus(refreshTokenValidityInSeconds, ChronoUnit.SECONDS);
        }

        JwtClaimsSet.Builder jwtClaimsSetBuilder = JwtClaimsSet.builder()
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .subject(SecurityUtil.extractFromPrincipal(authentication))
                .claim("user_info", userInfo);

        if (!refreshToken) {
            jwtClaimsSetBuilder.claim("scope", SecurityUtil.getAuthorities(authentication).collect(Collectors.toSet()));
        }

        JwtClaimsSet jwtClaimsSet = jwtClaimsSetBuilder.build();
        JwsHeader jwsHeader = JwsHeader.with(SecurityUtil.jwtAlgorithm).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
    }
}
