package com.sap.datacloud.main;

import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import com.sap.datacloud.service.RouteProcessor;


@SpringBootApplication
@ComponentScan("com.sap.datacloud")
@PropertySource("classpath:application.properties")
@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class OnboardApp implements CommandLineRunner {
	@Autowired
	private RouteProcessor routeProcessor;

	public static void main(String[] args) {
		SpringApplication.run(OnboardApp.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		routeProcessor.startRoutes();

	}

	@Bean
	public RestTemplate restProcessor(RestTemplateBuilder builder) {
		return builder.build();
	}


}
