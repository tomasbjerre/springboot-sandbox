package se.bjurr.springboot.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@WebMvcTest
class IntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testPing() throws Exception {

final MvcResult response = this.mockMvc.perform(get("/ping"))
    .andDo(MockMvcResultHandlers.print())
    .andExpect(status().isOk())
    .andReturn();

assertThat(response).isEqualTo("");
  }

}