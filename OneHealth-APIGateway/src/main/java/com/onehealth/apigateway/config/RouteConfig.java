package com.onehealth.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
//        		.route("default-path", r -> r.path("/").uri("lb://OneHealth-Default"))
                .route("onehealth-PatientUser", r -> r.path("/patientUser/**").uri("lb://onehealth-PatientUser"))
                .route("onehealth-PatientManagement", r -> r.path("/patientProfile/**").uri("lb://onehealth-PatientManagement"))
                .route("onehealth-LifeStyleAndHistory", r -> r.path("/lifeStyleAndHistory/**").uri("lb://onehealth-LifeStyleAndHistory"))
                .route("onehealth-DoctorAppointment", r -> r.path("/doctorAppointment/**").uri("lb://onehealth-Appointment"))
                // Add more routes for other microservices as needed
                .build();
    }
}
