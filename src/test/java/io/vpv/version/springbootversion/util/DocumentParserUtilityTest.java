package io.vpv.version.springbootversion.util;

import io.vpv.version.springbootversion.SpringBootVersionApplicationTests;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class DocumentParserUtilityTest extends SpringBootVersionApplicationTests {

    @Autowired
    DocumentParserUtility documentParserUtility;

    @Test
    public void getDocumentFromURL() {

        Document fromURL = documentParserUtility.getDocumentFromURL("http://httpbin.org/status/200");
        Assert.notNull(fromURL, "Expect a non null value");
    }


    @Test
    public void shouldExpectIlllegalArgumetException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Document fromURL = documentParserUtility.getDocumentFromURL(null);
        });
    }


    @Test
    public void shouldExpectRunTimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Document fromURL = documentParserUtility.getDocumentFromURL("http://httpbin.org/status/400");
        });
    }
}
