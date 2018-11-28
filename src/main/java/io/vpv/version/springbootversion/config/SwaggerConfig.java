package io.vpv.version.springbootversion.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .apiInfo(apiInfo())
                .select()
                .paths(paths())
                .build();
    }

    private Predicate<String> paths() {
        return or(regex("/api/.*"));
    }

    private Contact contact() {
        return new Contact("Venkateswara VP", "https://boottree.vpv.io", "contact@vpv.io");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("REST Spring Boot Dependency Project")
                .description("REST API for Accessing the dependency for Spring Boot Project")
                .termsOfServiceUrl("http://boottree.vpv.io/")
                .contact(contact())
                .license("Back to Home Page")
                .licenseUrl("/")
                .version("1.0")
                .build();
    }
}
