package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.modal.Dependency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vprasanna on 6/12/18.
 */
@Service
public class BootVersionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
                    .collect(Collectors.toList());
        } catch (Exception  e) {
            logger.error("Problem while getting the dependencies for {}", bootVersion, e);
            throw new RuntimeException("Problem while getting the dependencies for " + bootVersion, e);
        }
        return dependencies;
    }
}
