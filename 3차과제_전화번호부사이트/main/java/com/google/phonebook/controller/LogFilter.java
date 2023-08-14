package com.google.phonebook.controller;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFilter implements Filter { // 필터를 사용하려면 필터 인터페이스를 구현해야 한다.
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("log filter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("log filter doFilter");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		String uuid = UUID.randomUUID().toString(); // HTTP 요청을 구분하기 위한 요청당 임의의 uuid 생성

		try {
			logger.info("REQUEST [{}][{}]", uuid, requestURI);
			chain.doFilter(request, response);
		} catch (Exception e) {
			throw e;
		} finally {
			logger.info("RESPONSE [{}][{}]", uuid, requestURI);
		}

	}

	@Override
	public void destroy() {
		System.out.println("destroy");
		logger.info("log filter destroy");
	}
}