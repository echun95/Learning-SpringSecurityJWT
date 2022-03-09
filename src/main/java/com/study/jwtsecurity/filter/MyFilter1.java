package com.study.jwtsecurity.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        System.out.println("필터 1");
        // 토큰 : testToken
        //토큰은 id, pw이 정상적으로 들어와서 로그인이 완료되면 토큰을 생성해주고 그걸 응답해준다.
        //요청할 때마다 header에 Authorization에 value 값으로 토큰을 넣어 보낸다.
        //그때 토큰이 넘어오면 이 토큰을 검증한다.(RSA, HS256)
        if(req.getMethod().equals("POST")){
            String authorization = req.getHeader("Authorization");
            if(authorization.equals("testToken")){
                filterChain.doFilter(req, res);
            }else{
                PrintWriter outPrintWriter = res.getWriter();
                outPrintWriter.println("인증안됨");
            }
        }
    }
}
