package io.vpv.version.springbootversion.util;

import io.vpv.version.springbootversion.SpringBootVersionApplicationTests;
import org.jsoup.nodes.Document;
import org.junit.Test;
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


    @Test(expected = IllegalArgumentException.class)
    public void shouldExpectIlllegalArgumetException() {

        Document fromURL = documentParserUtility.getDocumentFromURL(null);
    }


    @Test(expected = RuntimeException.class)
    public void shouldExpectRunTimeException() {

        Document fromURL = documentParserUtility.getDocumentFromURL("http://httpbin.org/status/400");
    }
}