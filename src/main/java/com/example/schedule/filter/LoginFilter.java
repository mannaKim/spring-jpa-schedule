package com.example.schedule.filter;

import com.example.schedule.common.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/api/members/sign-up", "/api/auth/login", "/api/auth/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        log.info("로그인 필터 로직 실행");

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpServletRequest.getSession(false);
            if (session == null || session.getAttribute(Const.LOGIN_MEMBER) == null) {
                log.warn("로그인하지 않은 사용자 접근: {}", requestURI);

                httpServletResponse.setContentType("application/json");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                String jsonResponse = "{\"message\": \"로그인이 필요합니다.\", \"status\": 401}";
                httpServletResponse.getWriter().write(jsonResponse);
                return;
            }

            log.info("로그인 성공: {}", session.getAttribute(Const.LOGIN_MEMBER));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
