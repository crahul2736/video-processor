package com.walmart.video.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class VideoProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoProcessorApplication.class, args);
	}

	@Configuration
	public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

		@Override
		public void addResourceHandlers(final ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/media/images/**").addResourceLocations("file:///" + System.getProperty("user.dir") + "/src/main/media/images/");
		}
	}

}
