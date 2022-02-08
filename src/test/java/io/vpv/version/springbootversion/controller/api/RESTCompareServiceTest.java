package io.vpv.version.springbootversion.controller.api;

import io.vpv.version.springbootversion.SpringBootVersionMVCTests;
import io.vpv.version.springbootversion.data.MockDataProvider;
import io.vpv.version.springbootversion.service.BootVersionService;
import io.vpv.version.springbootversion.service.CompareService;
import io.vpv.version.springbootversion.util.DocumentParserUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

public class RESTCompareServiceTest extends SpringBootVersionMVCTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MockDataProvider mockDataProvider;
    @Mock
    DocumentParserUtility documentParserUtility;
    @InjectMocks
    private CompareService compareService;
    @Autowired
    private BootVersionService bootVersionService;

    @BeforeEach
    public void setUp() {
        this.documentParserUtility =
                mockDataProvider.initMockData(documentParserUtility);
        bootVersionService.setDocumentParserUtility(this.documentParserUtility);
        compareService = new CompareService(bootVersionService);
    }
    @Test
    @Disabled
    public void shouldReturnValidDependencies() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/api/compare/"
                        + "2.0.4.RELEASE"
                        + "/"
                        + "2.0.1.RELEASE"))
                        .andDo(print())
                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$", hasSize(1)))
                .andReturn().getResponse();

        Assert.notNull(result, "This should be able to compare list of dependencies");
    }
}
