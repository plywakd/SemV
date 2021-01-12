package com.pai.pai_demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/").hasAnyAuthority("user")
                .antMatchers("/users**").hasAnyAuthority("admin")
                .antMatchers("/users/**").hasAnyAuthority("admin", "user")
                .anyRequest().permitAll()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/login_process")
                .failureUrl("/login?error-true")
                .defaultSuccessUrl("/");
    }

    @Autowired
    DataSource dataSource;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT acc.email,acc.password,acc.status FROM accounts acc WHERE acc.email=?")
                .authoritiesByUsernameQuery("SELECT acc.email,r.role_name FROM accounts acc " +
                        "JOIN accounts_to_roles ar ON acc.account_id = ar.account_id JOIN roles r ON r.role_id = ar.role_id" +
                        "WHERE acc.email=?")
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
