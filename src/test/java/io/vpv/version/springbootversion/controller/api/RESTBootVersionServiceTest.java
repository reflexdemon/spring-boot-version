package io.vpv.version.springbootversion.controller.api;

import io.vpv.version.springbootversion.SpringBootVersionMVCTests;
import io.vpv.version.springbootversion.data.MockDataProvider;
import io.vpv.version.springbootversion.service.BootVersionService;
import io.vpv.version.springbootversion.util.DocumentParserUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vprasanna on 6/12/18.
 */

public class RESTBootVersionServiceTest extends SpringBootVersionMVCTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MockDataProvider mockDataProvider;

    @Mock
    DocumentParserUtility documentParserUtility;

    @Mock
    BootVersionService bootVersionService;

    @BeforeEach
    public void setUp() {
        this.documentParserUtility =
                mockDataProvider.initMockData(documentParserUtility);
        bootVersionService.setDocumentParserUtility(this.documentParserUtility);
    }
    @Test
    public void shouldReturnValidDependencies() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/api/dependency/"
                        + "2.0.2.RELEASE"))
                        .andDo(print())
                        .andExpect(status().isOk())
                .andReturn().getResponse();

        Assert.notNull(result, "This should return valid list of dependencies");
    }

    @Test
    public void shouldNotReturnValidDependencies() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(
                        get("/api/dependency/"
                                + "Junk")
                        )
                        .andDo(print())
                        .andExpect(status().is5xxServerError())
                .andReturn().getResponse();
        Assert.notNull(result, "Should throw Error for invalid request");
    }


    @Test
    public void shouldReturnValidReleases() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/api/releases/"))
                        .andDo(print())
                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$", hasSize(1)))
                        .andReturn().getResponse();

        Assert.notNull(result, "This should return valid list of dependencies");
    }


    @Test
    public void shouldReturnValidSnapshots() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/api/snapshot"))
                        .andDo(print())
                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$", hasSize(1)))
                        .andReturn().getResponse();

        Assert.notNull(result, "This should return valid list of dependencies");
    }

    @Test
    public void shouldReturnValidMilestones() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/api/milestones"))
                        .andDo(print())
                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$", hasSize(1)))
                        .andReturn().getResponse();

        Assert.notNull(result, "This should return valid list of dependencies");


    }

    @Test
    public void shouldReturnValidVersions() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/api/versions"))
                        .andDo(print())
                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$", hasSize(1)))
                        .andReturn().getResponse();

        Assert.notNull(result, "This should return valid list of dependencies");
    }
}
