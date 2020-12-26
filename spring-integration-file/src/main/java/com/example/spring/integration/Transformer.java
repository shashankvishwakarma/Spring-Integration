package com.example.spring.integration;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class Transformer {
    public String transform(String filePath) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return "Spring File Integration using Transformed: " + content;
    }
}
