package io.vpv.version.springbootversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//https://howtodoinjava.com/spring-boot2/spring-boot-cache-example/
@EnableCaching
public class SpringBootVersionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVersionApplication.class, args);
	}

	@Bean
	WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
		return (factory) -> factory.setRegisterDefaultServlet(true);
	}

}
