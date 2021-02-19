package com.example.CRUD_Spring_Boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan("com")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final SuccessUserHandler successUserHandler;

    public SecurityConfig(@Qualifier("userServices") UserDetailsService userDetailsService, SuccessUserHandler successUserHandler) {
        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;
        System.out.println("Запуск конструктора  SecurityConfig ");
        //System.out.println(userDetailsService.loadUserByUsername("1"));
    }

/*    private final SuccessUserHandler successUserHandler;

    public SecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
        System.out.println("Запуск конструктора  SecurityConfig ");
    }*/

    @Override
    protected void configure( HttpSecurity  httpSecurity) throws Exception {
        //System.out.println("configure from securityConfig");
        httpSecurity
                .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/login").anonymous()

                    .antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
                    .antMatchers("/user/**").access("hasAnyRole('ROLE_USER')")
                    //.anyRequest().authenticated()
                .and()
                    .formLogin()
                    //.loginPage("/login")
                    //.usernameParameter("j_username")
                    //.passwordParameter("j_password")
                    .successHandler(successUserHandler)
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login");
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
