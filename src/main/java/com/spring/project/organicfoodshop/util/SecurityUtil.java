package com.spring.project.organicfoodshop.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SecurityUtil {

    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;
    private final JwtEncoder jwtEncoder;

    @Value("${security.authentication.jwt.access-token-validity-in-seconds}")
    private long accessTokenValidityInSeconds;

    @Value("${security.authentication.jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenValidityInSeconds;

    public String createToken(Authentication authentication, boolean refreshToken) {
        Instant issuedAt = Instant.now();
        Instant expiresAt;
        if (!refreshToken) {
            expiresAt = issuedAt.plus(accessTokenValidityInSeconds, ChronoUnit.SECONDS);
        }else {
            expiresAt = issuedAt.plus(refreshTokenValidityInSeconds, ChronoUnit.SECONDS);
        }
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .subject(authentication.getName())
                .claim("user", authentication)
                .build();
        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
    }

    public static String extractPrincipal(Authentication authentication) {
        String result = null;
        if (authentication != null) {
            if(authentication.getPrincipal() instanceof UserDetails userDetails) {
                result = userDetails.getUsername();
            }else if (authentication.getPrincipal() instanceof Jwt jwt){
                result = jwt.getSubject();
            } else if (authentication.getPrincipal() instanceof String str) {
                result = str;
            }
        }
        return result;
    }

    public static Optional<String> getCurrentUserLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(extractPrincipal(authentication));
    }

    public static boolean hasCurrentUserAnyOfAuthorities(String... authorities) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && getAuthorities(authentication).anyMatch(authority -> Arrays.asList(authorities).contains(authority));
    }

    private static Stream<String> getAuthorities(Authentication authentication){
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
    }
}
