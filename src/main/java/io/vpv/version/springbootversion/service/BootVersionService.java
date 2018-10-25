package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.modal.Dependency;
import io.vpv.version.springbootversion.modal.VersionInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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



    @Cacheable("versionlist")
    public List<String> getVersionList() {
        List<String> versions = null;
        try {
            logger.debug("Listing Spring Versions");
            Document document = null;

            document = Jsoup.connect(versionlist).get();
            Elements allTables =
                    document.select(".grid").select(".versions");
                    versions = allTables.
                            select("a").
                            parallelStream().
                            map( element -> {
                                logger.info("element:" + element);
                                if (null != element && element.hasClass("vbtn")) {
                                    return element.text();
                                } else {
                                    return null;
                                }
                            }
                    ).
                    filter(item -> item != null)
                            .collect(toList());
        } catch (Exception  e) {
            logger.error("Problem while getting the list of spring boot versions", e);
            throw new RuntimeException("Problem while getting the list of spring boot versions", e);
        }

        return versions;
    }

    @Cacheable("milestonelist")
    public List<String> getMileStoneVersionList() {
        List<String> versions = null;
        logger.debug("Listing Milestone Spring Boot Versions");
        versions = getVersionsFromURL(milestonelist);
        return versions;
    }

    @Cacheable("snapshotlist")
    public List<String> getSnapshotVersionList() {
        List<String> versions = null;
        logger.debug("Listing Snapshot Spring Boot Versions");
        versions = getVersionsFromURL(snapshotlist);
        return versions;
    }


    @Cacheable("versioninfo")
    public VersionInfo getAllVersionInfo() {
        VersionInfo versions = new VersionInfo(getMileStoneVersionList(), getSnapshotVersionList());
        return versions;
    }

    @Cacheable("docVersions")
    public List<String> getDocumentedVersionList() {
        List<String> versions = null;
        logger.debug("Listing Documented Spring Boot Versions");
        versions = getVersionsFromURL(docVersions);
        versions.sort(reverseOrder(String::compareToIgnoreCase));
        return versions;
    }

    private List<String> getVersionsFromURL(String url) {
        logger.info("Making HTTP Call to {}", url);
        List<String> versions;
        try {
            Document document = null;

            document = Jsoup.connect(url).get();
            Elements allTables =
                    document.select("a");
            versions = allTables.
//                    next().//We need the second element
//                    select("a").
        parallelStream().filter(element -> element != null).
                            map(element -> element.text()).
                            filter(text -> !text.contains("..")).
                            filter(text -> !text.contains("maven")).
                            filter(text -> (text.indexOf('.') > 0)).
                            map(value -> value.substring(0, value.length() - 1)).
                            collect(toList());
        } catch (Exception e) {
            logger.error("Problem while getting the list of spring boot versions", e);
            throw new RuntimeException("Problem while getting the list of spring boot versions", e);
        }
        return versions;
    }

    @Cacheable(value = "dependency", key = "#bootVersion")
    public List<Dependency> getDependencies(final String bootVersion) {
        List<Dependency> dependencies = null;
        try {
            logger.debug("Searching dependencies for {}", bootVersion);
            String url = basePath + bootVersion + dependencyPage;
            Document document = null;

            document = Jsoup.connect(url).get();
            Elements allTables =
                    document.select(".informaltable");
            dependencies = allTables.select("tr").parallelStream().map(
                    getTd(bootVersion)
            ).filter(item -> item != null)
                    .collect(toList());
        } catch (Exception  e) {
            logger.error("Problem while getting the dependencies for {}", bootVersion, e);
            throw new RuntimeException("Problem while getting the dependencies for " + bootVersion, e);
        }
        return dependencies;
    }

    private Function<Element, Dependency> getTd(String bootVersion) {
        return element -> {
            List<String> values = element.select("td").eachText();
            if (values.size() >= 3) {
                String groupId = values.get(0);
                String artifactId = values.get(1);
                String version = values.get(2);
                Dependency dependency = new Dependency(bootVersion, groupId, artifactId, version);
//                            dependencies.add(dependency);
                return dependency;
            }
            return null;
        };
    }

}
