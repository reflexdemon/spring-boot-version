package io.vpv.version.springbootversion.controller.api;

import io.vpv.version.springbootversion.SpringBootVersionApplicationTests;
import io.vpv.version.springbootversion.modal.Dependency;
import io.vpv.version.springbootversion.modal.ErrorResponse;
import io.vpv.version.springbootversion.modal.VersionInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by vprasanna on 6/12/18.
 */
public class RESTBootVersionServiceTest extends SpringBootVersionApplicationTests {
    @Autowired
    private WebTestClient webClient;
    @Test
    public void shouldReturnValidDependencies() throws Exception {
        List<Dependency> result =
                this.webClient.get()
                        .uri("/api/dependency/"
                                + "2.0.2.RELEASE")
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(List.class)
                        .returnResult().getResponseBody();

        Assert.notEmpty(result, "This should return valid list of dependencies");
    }

    @Test
    public void shouldNotReturnValidDependencies() throws Exception {
        ErrorResponse result =
                this.webClient.get()
                        .uri("/api/dependency/"
                                + "Junk")
                        .exchange()
                        .expectStatus().is5xxServerError()
                        .expectBody(ErrorResponse.class)
                        .returnResult().getResponseBody();
        Assert.notNull(result, "Should throw Error for invalid request");

    }


    @Test
    public void shouldReturnValidReleases() throws Exception {
        List<Dependency> result =
                this.webClient.get()
                        .uri("/api/releases")
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(List.class)
                        .returnResult().getResponseBody();

        Assert.notEmpty(result, "This should return valid list of versions");
    }


    @Test
    public void shouldReturnValidSnapshots() throws Exception {
        List<Dependency> result =
                this.webClient.get()
                        .uri("/api/snapshot")
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(List.class)
                        .returnResult().getResponseBody();

        Assert.notEmpty(result, "This should return valid list of versions");
    }

    @Test
    public void shouldReturnValidMilestones() throws Exception {
        List<Dependency> result =
                this.webClient.get()
                        .uri("/api/milestones")
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(List.class)
                        .returnResult().getResponseBody();

        Assert.notEmpty(result, "This should return valid list of versions");
    }

    @Test
    public void shouldReturnValidVersions() throws Exception {
        VersionInfo result =
                this.webClient.get()
                        .uri("/api/versions")
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(VersionInfo.class)
                        .returnResult().getResponseBody();

        Assert.notEmpty(result.getMilestones(), "This should return valid list of versions");
        Assert.notEmpty(result.getSnapshots(), "This should return valid list of versions");
    }
}