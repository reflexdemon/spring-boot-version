package io.vpv.version.springbootversion.controller.page;

import io.vpv.version.springbootversion.SpringBootVersionMVCTests;
import io.vpv.version.springbootversion.data.MockDataProvider;
import io.vpv.version.springbootversion.service.BootVersionService;
import io.vpv.version.springbootversion.util.DocumentParserUtility;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DependencyControllerTest extends SpringBootVersionMVCTests {

    @Autowired
    MockDataProvider mockDataProvider;
    @Mock
    DocumentParserUtility documentParserUtility;
    @Mock
    BootVersionService bootVersionService;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.documentParserUtility =
                mockDataProvider.initMockData(documentParserUtility);
        bootVersionService.setDocumentParserUtility(this.documentParserUtility);
    }

    @Test
    public void shouldReturnAValidDependencyPageTemplate() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/dependency/"
                        + "2.0.2.RELEASE"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse();

        Assert.notNull(result, "This should return valid list of dependencies");
    }

    @Test
    public void shouldReturnAValidDependencyPageTemplateBadValue() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/dependency/"
                        + "JUNK"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse();

        Assert.notNull(result, "This should return valid list of dependencies");
    }

    @Test
    public void shouldReturnAValidDependencyPageTemplateWithQueryParam() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/dependency?bootVersion="
                        + "2.0.2.RELEASE"))
                        .andDo(print())
                        .andExpect(status().is3xxRedirection())
                        .andReturn().getResponse();

        Assert.notNull(result, "This should return valid list of dependencies");
    }

    @Test
    public void shouldReturnAValidDependencyPageTemplateWithQueryParamBadParam() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/dependency?badParam="
                        + "2.0.2.RELEASE"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse();

        Assert.notNull(result, "This should return valid list of dependencies");
    }
}