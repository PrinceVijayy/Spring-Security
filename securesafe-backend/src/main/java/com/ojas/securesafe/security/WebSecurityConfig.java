package com.ojas.securesafe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 * @author vk22051
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;
    private final CorsConfig corsConfig;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService, CorsConfig corsConfig) {
        this.userDetailsService = userDetailsService;
        this.corsConfig = corsConfig;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
//                    .antMatchers("securesafe/register","securesafe/hello")
//                        .permitAll()
//                    .anyRequest()
//                        .authenticated()
//                .and()
//                    .formLogin()
//                         .loginPage("/login")
//                    .permitAll()
//                .and()
//                    .logout()
//                        .logoutUrl("/logout")
//                        .permitAll
                .and()
                .sessionManagement()
                .sessionAuthenticationStrategy(customAuthenticationStrategy())
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/invalid-session")
                .maximumSessions(1)
                .expiredUrl("/session-expired");
    }
    @Bean
    public SessionAuthenticationStrategy customAuthenticationStrategy() {
        return new CustomSessionAuthenticationStrategy();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider
                = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(getPasswordEncoder());
        return provider;
    }

}
