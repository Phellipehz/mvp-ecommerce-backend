package com.ecommerce.backend.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//FIXME esta parte habilita o CORS, remover quando necessário

@Configuration
public class CORSConfiguration {

	@Bean
	   public WebMvcConfigurer corsConfigurer(){
		  return new WebMvcConfigurerAdapter(){
			  @Override
			  public void addCorsMappings(CorsRegistry registry){
				  registry.addMapping("/**")
				  		  .allowedOrigins("*")
				  		  .allowedMethods("*")
				  		  .allowedHeaders("*")
				  		  .allowCredentials(true);
			  }
		  };
	   }
	   
	
	
}


