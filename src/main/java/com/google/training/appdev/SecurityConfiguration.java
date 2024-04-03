package com.google.training.appdev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

/**
 * @since 1.2.2
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize->{
              authorize
                      .requestMatchers("/**")
                      .permitAll()
                      .requestMatchers("/api/**")
                      .authenticated();

            })
            .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()));

    return http.build();
  }


//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http.authorizeHttpRequests(authorize->{
//              authorize
//                      .requestMatchers("/",  "/index.html", "/index","/favicon.ico")
//                      .permitAll()
//                      .requestMatchers("/login/**")
//                      .permitAll()
//                      .requestMatchers("/css/**")
//                      .permitAll()
//                      .requestMatchers("/templates/**")
//                      .permitAll()
//                      .requestMatchers("/app/**")
//                      .permitAll()
//                      .requestMatchers("/answer")
//                      .authenticated();
//
//            })
//        .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()));
//
//    return http.build();
//  }

  @Bean
    public RestTemplate getRestTemplate() {
      return new RestTemplate();
  }
}
