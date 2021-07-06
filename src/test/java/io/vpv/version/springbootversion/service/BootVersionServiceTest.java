package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.SpringBootVersionApplicationTests;
import io.vpv.version.springbootversion.data.MockDataProvider;
import io.vpv.version.springbootversion.modal.DependencyDetails;
import io.vpv.version.springbootversion.modal.VersionInfo;
import io.vpv.version.springbootversion.util.DocumentParserUtility;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by vprasanna on 6/12/18.
 */
public class BootVersionServiceTest extends SpringBootVersionApplicationTests {

    private static final String[] SPRING_BOOT_VERSIONS = {
            "1.5.13.RELEASE",
            "1.5.14.BUILD-SNAPSHOT",
            "2.0.3.BUILD-SNAPSHOT",
            "2.1.0.BUILD-SNAPSHOT",
            "2.0.2.RELEASE"
    };
    @Autowired
    private BootVersionService bootVersionService;

    @Autowired
    MockDataProvider mockDataProvider;

    @Mock
    DocumentParserUtility documentParserUtility;

    @Before
    public void setUp() {
        this.documentParserUtility =
        mockDataProvider.initMockData(documentParserUtility);
        bootVersionService.setDocumentParserUtility(this.documentParserUtility);
    }

    @Test
    @Ignore
    public void testGetDependencies() throws Exception {

        for (String bootVersion :SPRING_BOOT_VERSIONS) {
            DependencyDetails output = bootVersionService.getDependencies(bootVersion);
            System.out.println("output:" + output);
            Assert.notNull(output, "Result is empty.");
            Assert.notEmpty(output.getDependencies(), "The list is empty.");
        }

    }

    @Test
    public void testGetVersionList() throws Exception {
        List<String> versionList = bootVersionService.getVersionList();
        System.out.println("output:" + versionList);
        Assert.notEmpty(versionList, "The list is empty.");
    }

    @Test
    public void testGetMileStoneVersionList() throws Exception {
        List<String> versionList = bootVersionService.getMileStoneVersionList();
        System.out.println("output:" + versionList);
        Assert.notEmpty(versionList, "The list is empty.");
    }


    @Test
    public void testGetSnapshotVersionList() throws Exception {
        List<String> versionList = bootVersionService.getSnapshotVersionList();
        System.out.println("output:" + versionList);
        Assert.notEmpty(versionList, "The list is empty.");
    }

    @Test
    public void testGetSDocVersionList() throws Exception {
        List<String> versionList = bootVersionService.getDocumentedVersionList();
        System.out.println("output:" + versionList);
        Assert.notEmpty(versionList, "The list is empty.");
    }

    @Test
    public void testAllVersions() throws Exception {
        VersionInfo versionList = bootVersionService.getAllVersionInfo();
        System.out.println("output:" + versionList);
        Assert.notNull(versionList, "We did not get the valid list");
    }
}