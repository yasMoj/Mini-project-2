package com.gruppprojekt2.kvarteret;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService () {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder().username("user").password("123").roles("USER").build());
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                /*.antMatchers("/images/**").permitAll()
                .antMatchers("/addItem").permitAll()
                .antMatchers("/newUser").permitAll()
                .antMatchers("/items").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")*/
                .anyRequest().authenticated()

                .and()

                .formLogin().defaultSuccessUrl("/items", true)

                .loginPage("/")
                .permitAll();
    }
}
