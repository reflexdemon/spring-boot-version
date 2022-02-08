package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.SpringBootVersionApplicationTests;
import io.vpv.version.springbootversion.data.MockDataProvider;
import io.vpv.version.springbootversion.modal.DependencyDetails;
import io.vpv.version.springbootversion.modal.VersionSummary;
import io.vpv.version.springbootversion.util.DocumentParserUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class CompareServiceTest extends SpringBootVersionApplicationTests {

    @Mock
    DocumentParserUtility documentParserUtility;
    //    @Autowired
    private CompareService compareService;
    @Autowired
    MockDataProvider mockDataProvider;
    @Autowired
    private BootVersionService bootVersionService;

    @BeforeEach
    public void setUp() {
        this.documentParserUtility =
                mockDataProvider.initMockData(documentParserUtility);
        bootVersionService.setDocumentParserUtility(this.documentParserUtility);
        compareService = new CompareService(bootVersionService);
    }
    @Test
    @Disabled
    public void shouldBeAbleToMerge() {
        String first = "1.1.2.RELEASE";
        String second = "2.0.1.RELEASE";

        VersionSummary versionSummary = compareService.merge(first, second);

        System.out.println("Summary:" + versionSummary);
        Assert.notNull(versionSummary, "Should return the summary`");
        Assert.notEmpty(versionSummary.getArtifacts(), "Should have artifacts listed");
    }

    @Test
    public void shouldBeAbleToMerge1() {
        String first = "1.1.2.RELEASE";
        String second = "";
        Assertions.assertThrows(RuntimeException.class, () -> {
            VersionSummary versionSummary = compareService.merge(first, second);

            System.out.println("Summary:" + versionSummary);
        });
    }

    @Test
    public void shouldBeAbleToMergeBadInput() {
        DependencyDetails first = null;
        DependencyDetails second = null;

        VersionSummary versionSummary = compareService.merge(first, second);

        System.out.println("Summary:" + versionSummary);
        Assert.isNull(versionSummary, "Should return the summary`");
    }
}
