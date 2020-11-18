package com.example.onboard.infrastructure.security;

import com.example.onboard.domain.model.security.CustomUser;
import com.example.onboard.infrastructure.security.constant.SpringSecurity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.host}")
    private String domainName;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    @Autowired
    @Qualifier("customDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * generate the access  token.
     *
     * @param authentication
     * @return String
     */
    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setClaims(createClaimsMap((CustomUser) authentication.getPrincipal())).setSubject(authentication.getName()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiryDate.getTime() * 1000))
                .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.encode(jwtSecret))
                .compact();
    }

    /**
     * @param token
     * @return Long
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(TextCodec.BASE64.encode(jwtSecret))
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    /**
     * Validate the token
     *
     * @param authToken
     * @return boolean(true / false)
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(TextCodec.BASE64.encode(jwtSecret)).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            LOGGER.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty.");
        }
        return false;
    }

    /**
     * creating claims metadata when generating the token
     *
     * @param userPrincipal
     * @return
     */
    private Map<String, Object> createClaimsMap(CustomUser userPrincipal) {
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put(SpringSecurity.USER_ID.getValue(), userPrincipal.getId());
        claimMap.put(SpringSecurity.USER_NAME.getValue(), userPrincipal.getUsername());
        final String authorities = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claimMap.put(SpringSecurity.ROLES.getValue(), authorities);
        claimMap.put(SpringSecurity.DOMAIN_NAME.getValue(), domainName);

        return claimMap;
    }

    /**
     * getting claims from context map
     *
     * @param token
     * @return
     */
    public Map<String, Object> getUserContextFromClaims(String token) {
        Map<String, Object> map = new HashMap<>();
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(TextCodec.BASE64.encode(jwtSecret))
                    .parseClaimsJws(token)
                    .getBody();
            if(!domainName.equalsIgnoreCase(claims.get(SpringSecurity.DOMAIN_NAME.getValue()).toString())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token not valid for this domain");
            }
            map.put(SpringSecurity.USER_ID.getValue(), claims.get(SpringSecurity.USER_ID.getValue()));
            map.put(SpringSecurity.USER_NAME.getValue(), claims.get(SpringSecurity.USER_NAME.getValue()));
            map.put(SpringSecurity.ROLES.getValue(), claims.get(SpringSecurity.ROLES.getValue()));

        } catch (Exception ex) {
            LOGGER.error(String.format("Error occurred when getting claims from context '%s'.", ex.getMessage()));

        }
        return map;
    }

    /**
     * Getting the token from Authentication header.
     * e.g Bearer your_token
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    /**
     * get Claims (payload) from the token.
     *
     * @param token
     * @return claims
     */

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(TextCodec.BASE64.encode(jwtSecret))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            LOGGER.error(String.format("Error occurred when getting the claims from token'%s'.", ex.getMessage()));
            claims = null;
        }
        return claims;
    }

    /**
     * get user details/user principal object
     *
     * @param claims
     * @return UserPrincipal
     */
    private CustomUser getUserPrincipalFromClaims(Claims claims) {
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(SpringSecurity.ROLES.getValue()).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        Long userId = Long.valueOf(claims.get(SpringSecurity.USER_ID.getValue()).toString());
        String userName = String.valueOf(claims.get(SpringSecurity.USER_NAME.getValue()));
        return new CustomUser(userId, userName, authorities);


    }

    /**
     * set the user authentication context in spring security context.
     *
     * @param authToken
     */
    public void setUserAuthentication(String authToken) {
        try {
            Claims claims = getAllClaimsFromToken(authToken);
            LOGGER.info("Token Validating");
            UserDetails userDetails = getUserPrincipalFromClaims(claims);
            // set user authentication in spring security context
            TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
            authentication.setToken(authToken);
            authentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            LOGGER.error(String.format("Error occurred when setting the auth object into context'%s'.", ex.getMessage()));
        }
    }

}
