package com.sk.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {

		return routeLocatorBuilder
				.routes()
				.route(p -> p
						.path("/payroll/leave/**")
						.filters(f -> f.rewritePath("/payroll/leave/(?<segment>.*)", "/${segment}"))
						.uri("lb://LEAVE")
				)
				.route(p -> p
						.path("/payroll/employee/**")
						.filters(f -> f.rewritePath("/payroll/employee/(?<segment>.*)", "/${segment}"))
						.uri("lb://EMPLOYEE-MICROSERVICE")
				)
				.route(p -> p
						.path("/payroll/report/**")
						.filters(f -> f.rewritePath("/payroll/report/(?<segment>.*)", "/${segment}"))
						.uri("lb://REPORT")
				)
				.build();


	}


	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);

	}

}
