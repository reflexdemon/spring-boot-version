package io.vpv.version.springbootversion.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by vprasanna on 11/1/18.
 */
public class Downloader {
    /**
     * The Constant BUFF_SIZE.
     */
    static final int BUFF_SIZE = 2 * 1024 * 1024; // 2MB

    private static final boolean verbose = true;
    public static String[] ENDPOINTS = {
            "https://docs.spring.io/spring-boot/docs/",
            "https://docs.spring.io/spring-boot/docs/1.1.2.RELEASE/reference/html/appendix-dependency-versions.html",
            "https://docs.spring.io/spring-boot/docs/1.5.13.RELEASE/reference/html/appendix-dependency-versions.html",
            "https://docs.spring.io/spring-boot/docs/1.5.14.BUILD-SNAPSHOT/reference/html/appendix-dependency-versions.html",
            "https://docs.spring.io/spring-boot/docs/2.0.1.RELEASE/reference/html/appendix-dependency-versions.html",
            "https://docs.spring.io/spring-boot/docs/2.0.2.RELEASE/reference/html/appendix-dependency-versions.html",
            "https://docs.spring.io/spring-boot/docs/2.0.3.BUILD-SNAPSHOT/reference/html/appendix-dependency-versions.html",
            "https://docs.spring.io/spring-boot/docs/2.0.4.RELEASE/reference/html/appendix-dependency-versions.html",
            "https://docs.spring.io/spring-boot/docs/2.1.0.BUILD-SNAPSHOT/reference/html/appendix-dependency-versions.html",
            "https://docs.spring.io/spring-boot/docs/Junk/reference/html/appendix-dependency-versions.html",
            "https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-parent",
            "https://repo.spring.io/milestone/org/springframework/boot/spring-boot-starter/",
            "https://repo.spring.io/snapshot/org/springframework/boot/spring-boot-starter/"
    };
    /**
     * The Constant OUTPUTDIR.
     */
    private static String outDir = ".";

    public static void main(String args[]) {
//        String target = Downloader.class.getClassLoader().getResource(".").getFile();
        String target = "./src/test/resources/mock-content/";

        int counter = 1;
        for (String endpoint : ENDPOINTS) {
            getResponse(endpoint, (target + counter++ + ".html"));
        }

    }


    /**
     * Just in time logger util for debugging.
     *
     * @param string the string
     */
    public static void log(final String string) {
        if (verbose) {
            System.out.println(new java.util.Date() + " " + string);
        }
    }

    /**
     * Make HTP Request.
     *
     * @param urlString the URL that needs to be invoked.
     * @param filename  the local file name that needs to be saved
     * @return the response
     */
    public static void getResponse(final String urlString, final String filename) {
        if (null == filename) {
            throw new RuntimeException(
                    "Missling filename to be saved. Or Try using getResponse(String urlString)");
        }
        FileOutputStream out = null;
        InputStream in = null;
        try {
            final URL url = new URL(urlString);
            log("Opening connection to " + urlString + "...");
            final URLConnection urlC = url.openConnection();
            // Copy resource to local file, use remote file
            // if no local file name specified
            in = url.openStream();
            // Print info about resource
            log("Copying resource (type: " + urlC.getContentType());
            out = new FileOutputStream(filename);

            final byte[] buffer = new byte[BUFF_SIZE];
            int count = 0;
            long size = 0;
            while ((count = in.read(buffer, 0, buffer.length)) > 0) {
                size += count;
                out.write(buffer, 0, count);
            }
            log("Read..." + size + "..bytes, [" + size / 1024 + "KB]");
        } catch (final MalformedURLException e) {
            System.err.println(e.toString());
            e.printStackTrace();
        } catch (final IOException e) {
            System.err.println(e.toString());
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (final IOException e) {
                    // Ignore
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (final IOException e) {
                    // Ignore
                }
            }
        }
    }
}
