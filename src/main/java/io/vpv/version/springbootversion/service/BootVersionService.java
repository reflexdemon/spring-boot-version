package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.modal.Dependency;
import io.vpv.version.springbootversion.modal.DependencyDetails;
import io.vpv.version.springbootversion.modal.VersionInfo;
import io.vpv.version.springbootversion.util.DocumentParserUtility;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.toList;

/**
 * Created by vprasanna on 6/12/18.
 */
@Service
public class BootVersionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void setDocumentParserUtility(DocumentParserUtility documentParserUtility) {
        this.documentParserUtility = documentParserUtility;
    }

    @Autowired
    DocumentParserUtility documentParserUtility;
    @Value("${io.vpv.version.endpoint.versionlist}")
    private String versionlist;
    @Value("${io.vpv.version.endpoint.milestonelist}")
    private String milestonelist;
    @Value("${io.vpv.version.endpoint.snapshotlist}")
    private String snapshotlist;
    @Value("${io.vpv.version.endpoint.docVersions}")
    private String docVersions;
    @Value("${io.vpv.version.endpoint.dependency.base}")
    private String basePath;
    @Value("${io.vpv.version.endpoint.dependency.dependencyPage}")
    private String dependencyPage;
    @Value("${io.vpv.version.endpoint.dependency.dependencyPageNew}")
    private String dependencyPageNew;

    @Cacheable("versionlist")
    public List<String> getVersionList() {
        List<String> versions = null;

        logger.debug("Listing Spring Versions");
        Document document = documentParserUtility.getDocumentFromURL(versionlist);

        Elements allTables =
                document.select(".grid").select(".versions");
            versions = allTables.
                    select("a").
                    parallelStream().
                    filter(Objects::nonNull).
                    filter( element -> element.hasClass("vbtn")).
                    map(Element::text).
                    collect(toList());

        return versions;
    }

    @Cacheable("milestonelist")
    public List<String> getMileStoneVersionList() {
        logger.debug("Listing Milestone Spring Boot Versions");
        return getVersionsFromURL(milestonelist);
    }

    @Cacheable("snapshotlist")
    public List<String> getSnapshotVersionList() {
        logger.debug("Listing Snapshot Spring Boot Versions");
        return getVersionsFromURL(snapshotlist);
    }


    @Cacheable("versioninfo")
    public VersionInfo getAllVersionInfo() {
        logger.debug("Listing All Boot Versions");
        return new VersionInfo(getMileStoneVersionList(), getSnapshotVersionList());
    }

    @Cacheable("docVersions")
    public List<String> getDocumentedVersionList() {

        logger.debug("Listing Documented Spring Boot Versions");
        List<String> versions = getVersionsFromURL(docVersions);
        //Get latest on Top
        versions.sort(reverseOrder(String::compareToIgnoreCase));
        return versions;
    }

    private List<String> getVersionsFromURL(String url) {
        List<String> versions;
        Document document = documentParserUtility.getDocumentFromURL(url);

        versions = document.select("a").
                stream().
                filter(Objects::nonNull).
                map(Element::text).
                filter(text -> !text.contains("..")).
                filter(text -> !text.contains("maven")).
                filter(text -> (text.indexOf('.') > 0)).
                map(value -> value.substring(0, value.length() - 1)).
                collect(toList());
        return versions;
    }

    @Cacheable(value = "dependency", key = "#bootVersion")
    public DependencyDetails getDependencies(final String bootVersion) {
        DependencyDetails dependencyDetails = null;
        List<Dependency> dependencies = null;
        try {
            logger.debug("Searching dependencies for {}", bootVersion);
            String url = basePath + bootVersion;
            Document document = documentParserUtility.getDocumentFromURL(url, dependencyPage, dependencyPageNew);

            Elements allTables =
                    document.select("body > div.appendix > div.informaltable > table");
            if (allTables.size() == 0) {
                allTables =
                        document.select("div.sect1:nth-child(2) > div:nth-child(2) > table:nth-child(2)");
            }
            dependencies = allTables.select("tr").
                    stream().
                    map(element -> element.select("td").eachText()).
                    filter(Objects::nonNull).
                    filter(values -> values.size() >= 3).
                    map(value -> new Dependency(bootVersion, value.get(0), value.get(1), value.get(2))).
                    collect(toList());
            dependencyDetails = new DependencyDetails(dependencies, url, bootVersion);
        } catch (Exception  e) {
            logger.error("Problem while getting the dependencies for {}", bootVersion, e);
            throw new RuntimeException("Problem while getting the dependencies for " + bootVersion, e);
        }
        return dependencyDetails;
    }

}
