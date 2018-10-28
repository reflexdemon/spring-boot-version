package io.vpv.version.springbootversion.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DocumentParserUtility {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Document getDocumentFromURL(String url) {
        logger.info("Requesting document from {}.", url);
        if (null == url) {
            throw new IllegalArgumentException("Parameter URL is missing");
        }

        Document document = null;

        try {
            return document = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("Unable to connect to " + url, e);
        }

    }
}
