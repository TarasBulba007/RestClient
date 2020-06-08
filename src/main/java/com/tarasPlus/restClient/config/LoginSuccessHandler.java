package com.tarasPlus.restClient.config;

import com.tarasPlus.restClient.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        boolean isAdmin = user.getRoles().stream().anyMatch(x -> x.getRole().contains("ADMIN"));
        String redirect = isAdmin ? "/admin" : "/user";
        httpServletResponse.sendRedirect(redirect);
    }
}
