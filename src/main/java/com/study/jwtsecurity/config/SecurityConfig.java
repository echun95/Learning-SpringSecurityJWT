package com.study.jwtsecurity.config;

import com.study.jwtsecurity.config.jwt.JwtAuthenticationFilter;
import com.study.jwtsecurity.config.jwt.JwtAuthorizationFilter;
import com.study.jwtsecurity.filter.MyFilter1;
import com.study.jwtsecurity.filter.MyFilter3;
import com.study.jwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class); //다른 필터들 보다 시큐리티 필터가 우선적으로 실행된다.

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세션 사용 x
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) //AuthenticationManager
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository)) //AuthenticationManager
                .addFilter(corsFilter) //@CrossOrigin(인증 X), 시큐리티 필터에 등록(인증 O)
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
}
