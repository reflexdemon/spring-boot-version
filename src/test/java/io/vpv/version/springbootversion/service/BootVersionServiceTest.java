package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.SpringBootVersionApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * Created by vprasanna on 6/12/18.
 */
public class BootVersionServiceTest extends SpringBootVersionApplicationTests {

    @Autowired
    private BootVersionService bootVersionService;

    private static final String[] SPRING_BOOT_VERSIONS = {
            "1.5.13.RELEASE",
            "1.5.14.BUILD-SNAPSHOT",
            "2.0.3.BUILD-SNAPSHOT",
            "2.1.0.BUILD-SNAPSHOT",
            "2.0.2.RELEASE"
    };

    @Test
    public void testGetDependencies() throws Exception {

        for (String bootVersion :SPRING_BOOT_VERSIONS) {
            Assert.notEmpty(bootVersionService.getDependencies(bootVersion), "The list is not empty.");
        }

    }
}