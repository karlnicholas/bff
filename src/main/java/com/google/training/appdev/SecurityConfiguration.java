/*
 * Copyright 2017-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
                      .requestMatchers("/",  "/index.html", "/favicon.ico")
                      .permitAll()
                      .requestMatchers("/css/**")
                      .permitAll()
                      .requestMatchers("/templates/**")
                      .permitAll()
                      .requestMatchers("/app/**")
                      .permitAll()
                      .requestMatchers("/answer")
                      .authenticated();

            })
        .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()));

    return http.build();
  }

  @Bean
    public RestTemplate getRestTemplate() {
      return new RestTemplate();
  }
}
