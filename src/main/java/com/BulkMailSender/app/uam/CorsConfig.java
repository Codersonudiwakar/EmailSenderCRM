package com.BulkMailSender.app.uam;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

 @Bean
 public CorsFilter corsFilter() {
     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     CorsConfiguration config = new CorsConfiguration();
     
     // Allow credentials (cookies, authorization headers)
     config.setAllowCredentials(true);
     
     // Allow all origins (for development) - restrict in production
     config.addAllowedOriginPattern("*");
     
     // Allow specific headers
     config.setAllowedHeaders(Arrays.asList(
         "Origin", "Content-Type", "Accept", "Authorization", 
         "X-Requested-With", "Access-Control-Request-Method", 
         "Access-Control-Request-Headers"
     ));
     
     // Allow specific methods
     config.setAllowedMethods(Arrays.asList(
         "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
     ));
     
     // Expose headers to frontend
     config.setExposedHeaders(Arrays.asList(
         "Authorization", "Content-Type", "Content-Disposition"
     ));
     
     // Apply this configuration to all paths
     source.registerCorsConfiguration("/**", config);
     
     return new CorsFilter(source);
 }
}
