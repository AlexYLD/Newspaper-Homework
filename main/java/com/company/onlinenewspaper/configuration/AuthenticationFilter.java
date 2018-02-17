package com.company.onlinenewspaper.configuration;

import com.company.onlinenewspaper.model.UserSession;
import com.company.onlinenewspaper.repository.impl.UserSessionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserSessionRepositoryImpl sessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null) {
            filterChain.doFilter(request, response);
            return;
        }

        UserSession userSession = sessionRepository.getBySessionId(header);
        if (userSession == null) {
            response.setStatus(401);
            response.getWriter().write("Log In required");
        }

        UserDetails userDetails = new User(
                userSession.getUser().getUsername(),
                userSession.getUser().getPassword(),
                true,
                true,
                true,
                true,
                userSession.getUser().getRoles().stream()
                        .map(x -> new SimpleGrantedAuthority("ROLE_" + x.toString()))
                        .collect(Collectors.toList()));

        UsernamePasswordAuthenticationToken securityUserToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(securityUserToken);
        filterChain.doFilter(request, response);
        return;
    }
}
