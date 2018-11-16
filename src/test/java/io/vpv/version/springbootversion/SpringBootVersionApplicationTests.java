package io.vpv.version.springbootversion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootVersionApplicationTests {


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void contextLoads() {
	}

}
