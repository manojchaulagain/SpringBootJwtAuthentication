package com.anoush.authentication.security;

import com.anoush.authentication.security.jwt.JwtAuthEntryPoint;
import com.anoush.authentication.security.jwt.JwtAuthTokenFilter;
import com.anoush.authentication.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsServiceImpl userDetailsService;

  private final JwtAuthEntryPoint unauthorizedHandler;

  @Autowired
  public WebSecurityConfig(
      UserDetailsServiceImpl userDetailsService, JwtAuthEntryPoint unauthorizedHandler) {
    this.userDetailsService = userDetailsService;
    this.unauthorizedHandler = unauthorizedHandler;
  }

  @Bean
  public JwtAuthTokenFilter authenticationJwtTokenFilter() {
    return new JwtAuthTokenFilter();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .cors()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        //                .antMatchers("/api/auth/**", "/graphiql").permitAll()
        .antMatchers("/api/auth/**")
        .permitAll()
        .antMatchers("/health/monitor")
        .permitAll()
        .antMatchers("/health/value")
        .permitAll()
        .antMatchers("/graphiql")
        .permitAll()
        .antMatchers("/graphiql/**")
        .permitAll()
        .antMatchers("/graphql")
        .permitAll()
        .antMatchers("/graphql/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(unauthorizedHandler)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    httpSecurity.addFilterBefore(
        authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
