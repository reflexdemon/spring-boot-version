package io.vpv.version.springbootversion.data;

import io.vpv.version.springbootversion.util.DocumentParserUtility;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static org.mockito.Mockito.when;

@Component
public class MockDataProvider {



    private static final Map<String, String> map = new HashMap<>();
    static {
        map.put("https://docs.spring.io/spring-boot/docs/","./mock-content/1.html");
        map.put("https://docs.spring.io/spring-boot/docs/1.1.2.RELEASE/reference/html/appendix-dependency-versions.html","./mock-content/2.html");
        map.put("https://docs.spring.io/spring-boot/docs/1.5.13.RELEASE/reference/html/appendix-dependency-versions.html","./mock-content/3.html");
        map.put("https://docs.spring.io/spring-boot/docs/1.5.14.BUILD-SNAPSHOT/reference/html/appendix-dependency-versions.html","./mock-content/4.html");
        map.put("https://docs.spring.io/spring-boot/docs/2.0.1.RELEASE/reference/html/appendix-dependency-versions.html","./mock-content/5.html");
        map.put("https://docs.spring.io/spring-boot/docs/2.0.2.RELEASE/reference/html/appendix-dependency-versions.html","./mock-content/6.html");
        map.put("https://docs.spring.io/spring-boot/docs/2.0.3.BUILD-SNAPSHOT/reference/html/appendix-dependency-versions.html","./mock-content/7.html");
        map.put("https://docs.spring.io/spring-boot/docs/2.0.4.RELEASE/reference/html/appendix-dependency-versions.html","./mock-content/8.html");
        map.put("https://docs.spring.io/spring-boot/docs/2.1.0.BUILD-SNAPSHOT/reference/html/appendix-dependency-versions.html","./mock-content/9.html");
        map.put("https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-parent","./mock-content/10.html");
        map.put("https://repo.spring.io/milestone/org/springframework/boot/spring-boot-starter/","./mock-content/11.html");
        map.put("https://repo.spring.io/snapshot/org/springframework/boot/spring-boot-starter/","./mock-content/12.html");
    }

    public DocumentParserUtility initMockData(DocumentParserUtility documentParserUtility) {
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String strDoc = getResourceFileAsString(
                    entry.getValue()
            );
            Document document = new Document(
                    strDoc
            );
            String dependencyPage = "/reference/html/appendix-dependency-versions.html";
            String dependencyPageNew = "/reference/html/dependency-versions.html";
            document.html(strDoc);
            when(documentParserUtility.getDocumentFromURL(entry.getKey()))
                    .thenReturn(document);
            when(documentParserUtility.getDocumentFromURL(entry.getKey(), dependencyPage, dependencyPageNew))
                    .thenReturn(document);
        }

        return documentParserUtility;
    }


    /**
     * Reads given resource file as a string.
     *
     * @param fileName the path to the resource file
     * @return the file's contents or null if the file could not be opened
     */
    public String getResourceFileAsString(String fileName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(joining(System.lineSeparator()));
        }
        return null;
    }
}
