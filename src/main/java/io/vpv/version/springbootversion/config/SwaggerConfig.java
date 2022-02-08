package io.vpv.version.springbootversion.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/api/**")
                .build();
    }


    private Contact contact() {
        Contact contact = new Contact();
        contact.setName("Venkateswara VP");
        contact.setUrl("https://boottree.vpv.io");
        contact.setEmail("contact@vpv.io");
        return contact;
    }

    private Info apiInfo() {
        return new Info()
                .title("REST Spring Boot Dependency Project")
                .description("REST API for Accessing the dependency for Spring Boot Project")
                .termsOfService("http://boottree.vpv.io/")
                .contact(contact())
                .license(license())
//                .license(License)
//                .licenseUrl("/")
                .version("1.0");
//                .build();
    }

    private License license() {
        return new License()
                .name("MIT")
                .url("/");
    }
}
