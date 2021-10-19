package se.bjurr.springboot.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import se.bjurr.springboot.application.api.ResolvedPropsDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Test
  public void testPing() throws Exception {

    final MvcResult response =
        this.mockMvc
            .perform(get("/ping"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andReturn();

    final ResolvedPropsDTO actual = this.fromJson(response, ResolvedPropsDTO.class);
    assertThat(actual.getMyStringListProperty()).containsOnly("first", "second");
    assertThat(actual.getMyStringProperty()).isEqualTo("the string value");
  }

  private <T> T fromJson(final MvcResult response, final Class<T> clazz)
      throws JsonProcessingException, JsonMappingException, UnsupportedEncodingException {
    return new ObjectMapper().readValue(response.getResponse().getContentAsString(), clazz);
  }
}
