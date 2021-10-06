package io.catalyte.training.patienthealth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Disables the login page for Spring boot and cross referencing
     */
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.httpBasic().disable();
        security.csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/users").authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
