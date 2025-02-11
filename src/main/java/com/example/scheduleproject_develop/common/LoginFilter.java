package com.example.scheduleproject_develop.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/", "/users/signup","/auth/signup", "/auth/login", "/session"};


    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request; //다운캐스팅
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response; //다운캐스팅

        log.info("로그인 필터 로직 실행");

        if(!isWhiteList(requestURI)) {

            HttpSession session = httpRequest.getSession(false);

            if(session == null || session.getId()==null) {
                throw new RuntimeException("로그인 해주세요");
            }

            log.info("로그인 성공!");
        }

        chain.doFilter(request,response);

    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
