package io.vpv.version.springbootversion.controller.page;

import io.vpv.version.springbootversion.SpringBootVersionMVCTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HomeControllerTest extends SpringBootVersionMVCTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldBeAbleToLoadThePage() throws Exception {
        MockHttpServletResponse result =
                this.mockMvc.perform(get("/"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse();

        Assert.notNull(result, "This should be able to get a non null value");
    }
}
