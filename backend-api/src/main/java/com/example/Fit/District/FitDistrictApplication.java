package com.example.Fit.District;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FitDistrictApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FitDistrictApplication.class, args);

		System.out.println(
				"CUSTOMER PAGE CONTROLLER BEANS: " +
						context.getBeanNamesForType(
								com.example.Fit.District.controller.CustomerPageController.class).length);

		var mappings = context.getBean(
				org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping.class);

		mappings.getHandlerMethods().forEach((mapping, method) -> {
			if (method.getBeanType().getSimpleName().equals("CustomerPageController")) {
				System.out.println("CUSTOMER PAGE MAPPING: " + mapping);
			}
		});
	}
}
