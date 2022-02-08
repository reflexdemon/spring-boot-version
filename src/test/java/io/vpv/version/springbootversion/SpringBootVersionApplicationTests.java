package io.vpv.version.springbootversion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import(ProjectInfoAutoConfiguration.class)
public class SpringBootVersionApplicationTests {


	@BeforeEach
	void setUp() {
		try {
			MockitoAnnotations.openMocks(this).close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void contextLoads() {
	}

}
