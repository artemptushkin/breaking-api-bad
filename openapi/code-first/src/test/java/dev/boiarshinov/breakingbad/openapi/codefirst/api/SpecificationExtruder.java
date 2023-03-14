package dev.boiarshinov.breakingbad.openapi.codefirst.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@MockMvcTest is not acceptable here. We need more context to get the specification
@SpringBootTest
@AutoConfigureMockMvc
public class SpecificationExtruder {

    @Autowired private MockMvc mockMvc;

    @Test
    void extrudeOpenapiSpecification() throws Exception {
        String openApiYaml = mockMvc.perform(get("/v3/api-docs.yaml"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertFalse(openApiYaml.isEmpty());

        writeToFile(openApiYaml);
    }

    private static final String FILENAME = "openapi.yaml";

    private void writeToFile(String openApiYaml) {
        File file = new File("build", FILENAME);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(openApiYaml);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to a file: " + file, e);
        }
    }
}
