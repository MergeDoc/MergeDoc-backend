package com.mergedoc.backend.security.filter;

import com.mergedoc.backend.security.provider.JwtProvider;
import com.mergedoc.backend.utils.cookie.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;
    private final CookieUtil cookieUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = null;
        Authentication authenticate;

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Cookie memberTokenCookie = cookieUtil.getCookie(req, "member_token");

        if (memberTokenCookie != null) {
            token = memberTokenCookie.getValue();
        }

        if (token != null && !jwtProvider.isTokenExpired(token)) {
            try {
                String emailFromToken = jwtProvider.getEmailFromToken(token);
                authenticate = jwtProvider.
                        authenticate(new UsernamePasswordAuthenticationToken(emailFromToken, ""));
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            } catch (Exception e) {
            }
        }
        chain.doFilter(request, response);
    }
}
