package com.example.irctc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class E2EIntegrationTest {
  @Autowired MockMvc mvc;
  @Autowired ObjectMapper om;

  @Test
  void seed_search_book_pnr() throws Exception {
    mvc.perform(post("/api/admin/seed")).andExpect(status().isOk());

    String date = LocalDate.now().plusDays(1).toString();

    String sr = mvc.perform(post("/api/search")
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(Map.of(
                "from", "NDLS",
                "to", "BPL",
                "date", date,
                "coachClass", "THIRD_AC"
            )))
        ).andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    // Book 2 pax
    String br = mvc.perform(post("/api/booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(Map.of(
                "trainNumber", "12952",
                "from", "NDLS",
                "to", "BPL",
                "date", date,
                "coachClass", "THIRD_AC",
                "passengers", new Object[]{
                    Map.of("name","Alice","age",30,"gender","FEMALE"),
                    Map.of("name","Bob","age",28,"gender","MALE")
                }
            )))
        ).andExpect(status().isOk()).andExpect(jsonPath("$.pnr").exists())
        .andReturn().getResponse().getContentAsString();

    Map<?,?> booked = om.readValue(br, Map.class);
    String pnr = (String) booked.get("pnr");

    mvc.perform(get("/api/pnr/" + pnr))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.pnr").value(pnr))
        .andExpect(jsonPath("$.trainNumber").value("12952"))
        .andExpect(jsonPath("$.from").value("NDLS"))
        .andExpect(jsonPath("$.to").value("BPL"))
        .andExpect(jsonPath("$.coachClass").value("THIRD_AC"));
  }
}
