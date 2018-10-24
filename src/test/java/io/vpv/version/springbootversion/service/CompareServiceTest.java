package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.SpringBootVersionApplicationTests;
import io.vpv.version.springbootversion.modal.VersionSummary;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import static org.junit.Assert.*;

public class CompareServiceTest extends SpringBootVersionApplicationTests {

    @Autowired
    private CompareService compareService;

    @Test
    public void shouldBeAbleToMerge() {
        String first = "1.1.2.RELEASE";
        String second = "2.0.1.RELEASE";

        VersionSummary versionSummary = compareService.merge(first, second);

        System.out.println("Summary:" + versionSummary);
        Assert.notNull(versionSummary, "Should return the summary`");
        Assert.notEmpty(versionSummary.getArtifacts(), "Should have artifacts listed");
    }
}