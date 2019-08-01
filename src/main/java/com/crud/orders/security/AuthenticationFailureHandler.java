package com.crud.orders.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "failed login attempt. invalid username or password";
        if(exception.getMessage().equalsIgnoreCase("blocked")) {
            errorMessage = "you have been blocked for 3 unsuccessful login attempt";
        }
        System.out.println("path "+request.getPathInfo());
        response.sendRedirect(request.getPathInfo());
    }

}
