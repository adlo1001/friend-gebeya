package com.gebeya.Friend;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class FriendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendApplication.class, args);
	}

	@Bean
	public LocaleResolver localResolver() {
		SessionLocaleResolver sessionLocalResolver = new SessionLocaleResolver();
		sessionLocalResolver.setDefaultLocale(Locale.US);
		return sessionLocalResolver;
	}

}
