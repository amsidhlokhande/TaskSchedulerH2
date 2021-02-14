package com.amsidh.mvc.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.Objects;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] propertiesSkipped = getPermitAllUrls();
        http.authorizeRequests().antMatchers(propertiesSkipped).permitAll()
                .anyRequest()
                .authenticated()
                .and().userDetailsService(userDetailsServiceBean())
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Override
    public UserDetailsService userDetailsServiceBean() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails amsidhUserDetails = User.builder().username("amsidh")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("Pass@123").roles(new String[]{}).build();

        UserDetails adithiUserDetails = User.builder().username("adithi")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("Pass@123").roles(new String[]{}).build();

        inMemoryUserDetailsManager.createUser(amsidhUserDetails);
        inMemoryUserDetailsManager.createUser(adithiUserDetails);

        return inMemoryUserDetailsManager;

    }

    private String[] getPermitAllUrls() {
        System.out.println("environment :"+environment);
        System.out.println("environment.getProperty(\"skip.spring.security.urls\") :"+environment.getProperty("skip.spring.security.urls"));
        String[] propertiesSkipped = environment.getProperty("skip.spring.security.urls", String[].class);
        System.out.println("propertiesSkipped :"+propertiesSkipped);
        System.out.println("propertiesSkipped "+ propertiesSkipped.length);
        Arrays.asList(Objects.requireNonNull(propertiesSkipped)).forEach(prop->System.out.print(prop + ", "));
        return propertiesSkipped;
    }
}
