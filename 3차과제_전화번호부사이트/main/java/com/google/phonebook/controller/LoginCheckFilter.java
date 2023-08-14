package com.google.phonebook.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PatternMatchUtils;



public class LoginCheckFilter implements Filter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String[] whiteList = { "/phonebook/login/*"};

    @Override
    public void doFilter(ServletRequest request
    					, ServletResponse response
    					, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse)response;
        
        
        try {
            logger.info("인증 체크 필터 시작 {}", requestURI);
            if (isLoginCheckPath(requestURI)) {
            	
                logger.info("인증 케트 로직 실행", requestURI);
                HttpSession session = httpRequest.getSession(false);
                
                if (session == null || session.getAttribute("loginMember") == null) {

                    logger.info("미인증 사용자임", requestURI);
                    
                    // 로그인 페이지로 보내버린뒤 다시 조회할 페이지를넣어준다.
                    httpResponse.sendRedirect("/phonebook/beforelogin");
                    return;
                }
                
            }
            chain.doFilter(request,response);
        } catch (Exception e) {
            throw e; // 예외 로깅 가능 하지만, 톰캣 까지 예외를 보내줘야 함.
        } finally {
            logger.info("인증 체크 필터 종료 {}", requestURI);
        }
    }


    private boolean isLoginCheckPath(String requestURI) {
        return PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}