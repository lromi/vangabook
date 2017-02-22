package com.lrom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;

@SpringBootApplication
public class VangaBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(VangaBookApplication.class, args);
	}


	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}
}
