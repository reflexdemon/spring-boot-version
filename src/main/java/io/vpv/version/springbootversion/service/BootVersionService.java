package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.modal.Dependency;
import io.vpv.version.springbootversion.modal.VersionInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by vprasanna on 6/12/18.
 */
@Service
public class BootVersionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public List<String> getVersionList() {
        List<String> versions = null;
        try {
            logger.debug("Listing Spring Versions");
            String url = "https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-parent";
            Document document = null;

            document = Jsoup.connect(url).get();
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

    public List<String> getMileStoneVersionList() {
        List<String> versions = null;
        logger.debug("Listing Milestone Spring Boot Versions");
        String url = "https://repo.spring.io/milestone/org/springframework/boot/spring-boot-starter/";
        versions = getVersionsFromURL(url);

        return versions;
    }

    public List<String> getSnapshotVersionList() {
        List<String> versions = null;
        logger.debug("Listing Snapshot Spring Boot Versions");
        String url = "https://repo.spring.io/snapshot/org/springframework/boot/spring-boot-starter/";
        versions = getVersionsFromURL(url);
        return versions;
    }


    public VersionInfo getAllVersionInfo() {
        VersionInfo versions = new VersionInfo(getMileStoneVersionList(), getSnapshotVersionList());
        return versions;
    }

    private List<String> getVersionsFromURL(String url) {
        List<String> versions;
        try {
            Document document = null;

            document = Jsoup.connect(url).get();
            Elements allTables =
                    document.select("a");
            versions = allTables.
//                    next().//We need the second element
//                    select("a").
        parallelStream().
                            map(element -> {
                                        logger.info("Mile Stone: element:" + element);
                                        if (null != element) {
                                            return element.text();
                                        } else {
                                            return null;
                                        }
                                    }
                            ).
                            filter(item -> item != null).
                            filter(item -> !item.contains("..")).
                            map(value -> value.substring(0, value.length() - 1)).
                            collect(toList());
        } catch (Exception e) {
            logger.error("Problem while getting the list of spring boot versions", e);
            throw new RuntimeException("Problem while getting the list of spring boot versions", e);
        }
        return versions;
    }

    public List<Dependency> getDependencies(final String bootVersion) {
        List<Dependency> dependencies = null;
        try {
            logger.debug("Searching dependencies for {}", bootVersion);
            String url = "https://docs.spring.io/spring-boot/docs/"
                    + bootVersion
                    + "/reference/html/appendix-dependency-versions.html";
            Document document = null;

            document = Jsoup.connect(url).get();
            Elements allTables =
                    document.select(".informaltable");
            dependencies = allTables.select("tr").parallelStream().map(
                    element -> {
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
                    }
            ).filter(item -> item != null)
                    .collect(toList());
        } catch (Exception  e) {
            logger.error("Problem while getting the dependencies for {}", bootVersion, e);
            throw new RuntimeException("Problem while getting the dependencies for " + bootVersion, e);
        }
        return dependencies;
    }

}
