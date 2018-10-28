package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.modal.Dependency;
import io.vpv.version.springbootversion.modal.VersionInfo;
import io.vpv.version.springbootversion.util.DocumentParserUtility;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.toList;

/**
 * Created by vprasanna on 6/12/18.
 */
@Service
public class BootVersionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Autowired
    DocumentParserUtility documentParserUtility;

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
                    filter( element -> null != element).
                    filter( element -> element.hasClass("vbtn")).
                    map( element -> element.text()
            ).collect(toList());

        return versions;
    }

    @Cacheable("milestonelist")
    public List<String> getMileStoneVersionList() {
        logger.debug("Listing Milestone Spring Boot Versions");
        List<String> versions = getVersionsFromURL(milestonelist);
        return versions;
    }

    @Cacheable("snapshotlist")
    public List<String> getSnapshotVersionList() {
        logger.debug("Listing Snapshot Spring Boot Versions");
        List<String> versions = getVersionsFromURL(snapshotlist);
        return versions;
    }


    @Cacheable("versioninfo")
    public VersionInfo getAllVersionInfo() {
        logger.debug("Listing All Boot Versions");
        VersionInfo versions = new VersionInfo(getMileStoneVersionList(), getSnapshotVersionList());
        return versions;
    }

    @Cacheable("docVersions")
    public List<String> getDocumentedVersionList() {

        logger.debug("Listing Documented Spring Boot Versions");
        List<String> versions = getVersionsFromURL(docVersions);
        versions.sort(reverseOrder(String::compareToIgnoreCase));
        return versions;
    }

    private List<String> getVersionsFromURL(String url) {
        logger.info("Making HTTP Call to {}", url);
        List<String> versions;
        Document document = documentParserUtility.getDocumentFromURL(url);

        versions = document.select("a").
                parallelStream().
                filter(element -> element != null).
                map(element -> element.text()).
                filter(text -> !text.contains("..")).
                filter(text -> !text.contains("maven")).
                filter(text -> (text.indexOf('.') > 0)).
                map(value -> value.substring(0, value.length() - 1)).
                collect(toList());
        return versions;
    }

    @Cacheable(value = "dependency", key = "#bootVersion")
    public List<Dependency> getDependencies(final String bootVersion) {
        List<Dependency> dependencies = null;
        try {
            logger.debug("Searching dependencies for {}", bootVersion);
            String url = basePath + bootVersion + dependencyPage;
            Document document =  Jsoup.connect(url).get();
            Elements allTables =
                    document.select(".informaltable");
            dependencies = allTables.select("tr").parallelStream().
                    map(element -> element.select("td").eachText()).
                    filter(values -> null != values).
                    filter(values -> values.size() >= 3).
                    map (value -> new Dependency(bootVersion, value.get(0), value.get(1), value.get(2)))
                    .collect(toList());
        } catch (Exception  e) {
            logger.error("Problem while getting the dependencies for {}", bootVersion, e);
            throw new RuntimeException("Problem while getting the dependencies for " + bootVersion, e);
        }
        return dependencies;
    }

}
