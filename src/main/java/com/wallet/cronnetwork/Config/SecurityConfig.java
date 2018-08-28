package com.wallet.cronnetwork.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    CAuthProvider authProvider;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**")
                .antMatchers("/js/**").antMatchers("/img/**");
        //web.ignoring().antMatchers("/res/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/resetPw").permitAll()
                .antMatchers("/loginOtp").access("hasRole('ROLE_TEMPORARY')")
                .antMatchers("/**").access(
                "hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");

        http.csrf().disable();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login_error").
                usernameParameter("username").passwordParameter("password").permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);



        http.exceptionHandling().accessDeniedPage("/login");

/*
                http.formLogin()
       			.loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/error").
       	usernameParameter("username").passwordParameter("password")
       			.permitAll().and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .csrf().disable()
        .exceptionHandling().accessDeniedPage("/login");
*/

        http.authenticationProvider(authProvider);
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    }
}
