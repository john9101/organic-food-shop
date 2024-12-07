package com.spring.project.organicfoodshop.util;

//import com.spring.project.organicfoodshop.domain.response.auth.InitializedPrincipalResponse;
import com.spring.project.organicfoodshop.domain.response.auth.LoggedInResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class SecurityUtil {
    public static final MacAlgorithm jwtAlgorithm = MacAlgorithm.HS512;

    public static String extractFromPrincipal(Authentication authentication) {
        String result = null;
        if (authentication != null) {
            if(authentication.getPrincipal() instanceof UserDetails userDetails) {
                result = userDetails.getUsername();
            }else if (authentication.getPrincipal() instanceof Jwt jwt){
                result = jwt.getSubject();
            } else if (authentication.getPrincipal() instanceof String string) {
                result = string;
            }
//            else if (authentication.getPrincipal() instanceof LoggedInResponse.InitializedPrincipal initializedPrincipal) {
//                result = initializedPrincipal.getEmail();
//            }
        }
        return result;
    }

    public static Optional<String> getCurrentUserPrincipal(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractFromPrincipal(securityContext.getAuthentication()));
    }

    public static Optional<String> getCurrentUserCredentials() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getCredentials() instanceof String)
                .map(authentication -> (String) authentication.getCredentials());
    }


    public static boolean hasCurrentUserAnyOfAuthorities(String... authorities) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && getAuthorities(authentication).anyMatch(authority -> Arrays.asList(authorities).contains(authority));
    }

    public static Stream<String> getAuthorities(Authentication authentication){
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
    }
}
