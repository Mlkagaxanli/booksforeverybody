package com.developia.booksforeverybody.config;

import com.developia.booksforeverybody.dao.entity.RoleStatus;
import com.developia.booksforeverybody.service.MyUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder passwordEncoder;
    private MyUserDetailsService userDetailsService;

    public WebSecurityConfig(BCryptPasswordEncoder passwordEncoder,
                             MyUserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;

    }
    protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception{
        managerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }
    protected void configure(HttpSecurity security) throws Exception{
        security.
                authorizeRequests()
                .antMatchers("/books/**", "/users/sign-up","/users/login").permitAll()
                .antMatchers("/carts/**").hasAuthority(RoleStatus.USER.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .passwordParameter("password")
                .usernameParameter("username")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
                .logoutSuccessUrl("/books")
                .and().exceptionHandling().accessDeniedPage("//access-denied");

    }
    public void configure(WebSecurity security){
        security.ignoring()
                .antMatchers("/static/**","/images/**");
    }
}
