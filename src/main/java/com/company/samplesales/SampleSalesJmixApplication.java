package com.company.samplesales;

import io.jmix.notifications.NotificationType;
import io.jmix.notifications.NotificationTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@SpringBootApplication
public class SampleSalesJmixApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSalesJmixApplication.class, args);
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix="main.datasource")
	DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Autowired
	private NotificationTypesRepository notificationTypesRepository;

	@PostConstruct
	public void postConstruct() {
		notificationTypesRepository.registerTypes(
				new NotificationType("info", "INFO_CIRCLE"),
				new NotificationType("warn", "WARNING")
		);
	}
}
