package io.vpv.version.springbootversion.controller.page;

import io.vpv.version.springbootversion.SpringBootVersionMVCTests;
import io.vpv.version.springbootversion.data.MockDataProvider;
import io.vpv.version.springbootversion.service.BootVersionService;
import io.vpv.version.springbootversion.service.CompareService;
import io.vpv.version.springbootversion.util.DocumentParserUtility;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CompareControllerTest extends SpringBootVersionMVCTests {

    @Autowired
    MockDataProvider mockDataProvider;
    @Mock
    DocumentParserUtility documentParserUtility;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private CompareService compareService;
    @Autowired
    private BootVersionService bootVersionService;

    @Before
    public void setUp() {
        this.documentParserUtility =
                mockDataProvider.initMockData(documentParserUtility);
        bootVersionService.setDocumentParserUtility(this.documentParserUtility);
        compareService = new CompareService(bootVersionService);
    }

    @Test
    public void shouldReturnValidDependencies() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/compare/"
                        + "2.0.4.RELEASE"
                        + "/"
                        + "2.0.1.RELEASE"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse();

        Assert.notNull(result, "This should be able to compare list of dependencies");
    }


    @Test
    public void shouldReturnValidDependenciesWithParam() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/compare?firstVersion="
                        + "2.0.4.RELEASE"
                        + "&secondVersion"
                        + "2.0.1.RELEASE"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse();

        Assert.notNull(result, "This should be able to compare list of dependencies");
    }


    @Test
    public void shouldReturnValidDependenciesDefault() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/compare"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse();

        Assert.notNull(result, "This should be able to compare list of dependencies");
    }
}