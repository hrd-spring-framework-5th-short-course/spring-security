package com.example.demospringsecurity.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
public class CustomLoginSuccess implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException,
            ServletException {


        // set session time for use in second,
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setMaxInactiveInterval(3600);


        String redirectUrl =
                (String) httpServletRequest.getSession().getAttribute("USER_REQUEST_URL");

//        String urlToRedirect = null;
//
//        for (GrantedAuthority authority: authentication.getAuthorities()
//             ) {
//
//            System.out.println(authority.getAuthority());
//
//            if ( authority.getAuthority().equals("ROLE_ADMIN")){
//                urlToRedirect = "/admin";
//            }
//
//            else if (authority.getAuthority().equals("ROLE_DBA")) {
//                urlToRedirect = "/dba";
//            }
//
//            else if (authority.getAuthority().equals("ROLE_USER")){
//                urlToRedirect = "/user";
//            }
//
//            else {
//                urlToRedirect = "/home";
//            }
//        }

        httpServletResponse.sendRedirect(redirectUrl);
    }
}
