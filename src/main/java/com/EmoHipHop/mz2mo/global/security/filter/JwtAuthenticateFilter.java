package com.EmoHipHop.mz2mo.global.security.filter;

import com.EmoHipHop.mz2mo.global.security.data.type.TokenType;
import com.EmoHipHop.mz2mo.global.security.exception.LoginException;
import com.EmoHipHop.mz2mo.infra.jwt.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtAuthenticateFilter extends OncePerRequestFilter {
    private static final String TOKEN_PREFIX = "Bearer ";

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        boolean isValidate = validateHeader(header);
        if(!isValidate) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = getToken(header);
        String userId = getUserId(token);
        if(SecurityContextHolder.getContext().getAuthentication() == null)
            saveToContext(userDetailsService.loadUserByUsername(userId), request);

        filterChain.doFilter(request, response);
    }

    private void saveToContext(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private boolean validateHeader(String header) {
        return header != null && header.startsWith(TOKEN_PREFIX);
    }

    private String getUserId(String token) {
        Map<String, String> payload = jwtTokenUtil.decode(token);
        validatePayload(payload);
        return payload.get("userId");
    }

    private String getToken(String header) {
        return header.substring(TOKEN_PREFIX.length());
    }

    private void validatePayload(Map<String, String> payload) {
        if(isFormatWrong(payload)) throw new LoginException("????????? ???????????? ????????? ????????? ????????????!");
        if(isNotRefreshToken(payload)) throw new LoginException("?????? ????????? ?????????????????? ????????????!");
        if(isExpired(payload)) throw new LoginException("?????? ????????? ????????????????????????!");
    }

    private boolean isFormatWrong(Map<String, String> payload) {
        return Stream
                .of("type", "expiration", "id")
                .noneMatch(payload::containsKey);
    }

    private boolean isNotRefreshToken(Map<String, String> payload) {
        return !payload.get("type").equals(TokenType.LOGIN_ACCESS.getKey());
    }

    private boolean isExpired(Map<String, String> payload) {
        return Boolean.parseBoolean(payload.get("expiration"));
    }
}
