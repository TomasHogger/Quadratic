package controller;

import com.example.demo.DemoApplication;
import com.example.demo.dto.request.QuadraticRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class MathControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testOkResult() throws Exception {
        String request = mapper.writeValueAsString(new QuadraticRequestDto(
                new BigDecimal(16), new BigDecimal(-8), new BigDecimal(1)));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/solve_quadratic_equation/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testBadRequestResult() throws Exception {
        String request = mapper.writeValueAsString(new QuadraticRequestDto(
                new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/solve_quadratic_equation/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
