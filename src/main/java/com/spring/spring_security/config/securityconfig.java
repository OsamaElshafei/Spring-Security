package com.spring.spring_security.config;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
public class securityconfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests((requests) -> {
            (requests.anyRequest()).authenticated();
        });
        http.formLogin();
        http.httpBasic();*/
        /*http.authorizeRequests().anyRequest().permitAll()
                .and().formLogin().and().httpBasic();*/
        /*http.authorizeRequests().anyRequest().denyAll()
                .and().formLogin().and().httpBasic();*/
        http.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration() ;
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setMaxAge(2500L);
                return config;
            }
        }).and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests()
                .antMatchers("/engineer/*").authenticated()
                .antMatchers("/about/*").hasAuthority("edit")
                .antMatchers("/connect/*").hasAuthority("write")
                .antMatchers("/foot/*").permitAll()
                .and().formLogin().and().httpBasic();
    }
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("islam").password("12345").authorities("admin")
                .and().withUser("ahmed").password("00000").authorities("player")
                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    }*/
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails userDetails_1 = User.withUsername("islam").password("12345").authorities("admin").build();
        UserDetails userDetails_2 = User.withUsername("ahmed").password("00000").authorities("player").build();
        inMemoryUserDetailsManager.createUser(userDetails_1);
        inMemoryUserDetailsManager.createUser(userDetails_2);
        auth.userDetailsService(inMemoryUserDetailsManager);
    }*/

    /*
        @Bean
        public UserDetailsService userDetailsService(DataSource dataSource){
            return new JdbcUserDetailsManager(dataSource);
        }

    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

