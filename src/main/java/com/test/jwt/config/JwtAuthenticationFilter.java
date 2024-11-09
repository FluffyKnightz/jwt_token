package com.test.jwt.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        // Filter cookies by name
        // Get the value of the cookie
        // Get the first matching cookie
        final String cookie = cookies != null ? STR."Bearer \{Arrays.stream(cookies)
                .filter(c -> "jwtToken".equals(c.getName()))  // Filter cookies by name
                .map(Cookie::getValue)  // Get the value of the cookie
                .findFirst()  // Get the first matching cookie
                .orElse(null)}"
                : null;


        final String jwt;
        final String name;
        if (cookie == null || !cookie.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

//        Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha3oiLCJpYXQiOjE3Mjk1MzMzNDcsImV4cCI6MTcyOTUzNDc4N30.9FxzfKGvxZA9sYtemhV2WbT2_BO4nBiTpmByMl7n2Qs
//        after "Bearer " its 7

        jwt = cookie.substring(7);
        name = jwtService.extractUsername(jwt);



        if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            MyUserDetails myUserDetails = myUserDetailsService.loadUserByUsername(name);
            if (jwtService.isTokenValid(jwt, myUserDetails)) {

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        myUserDetails,
                        null,
                        myUserDetails.getAuthorities()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
