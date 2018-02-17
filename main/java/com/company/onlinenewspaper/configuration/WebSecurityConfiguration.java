package com.company.onlinenewspaper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/users/register**").permitAll()
                .antMatchers("/users/login**").permitAll()
                .antMatchers("/users/get").hasRole("READER")
                .antMatchers("/users/get/**").hasRole("WRITER")
                .antMatchers("/users/promote/**").hasRole("ADMIN")
                .antMatchers("/users/demote/**").hasRole("ADMIN")
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
