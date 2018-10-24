package io.vpv.version.springbootversion.controller.api;

import io.vpv.version.springbootversion.SpringBootVersionMVCTests;
import org.junit.Test;
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
    @Test
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