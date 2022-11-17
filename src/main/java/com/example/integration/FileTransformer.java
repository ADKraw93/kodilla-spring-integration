package com.example.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.file.FileReadingMessageSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileTransformer {

    public String transformFile(String fileName) throws IOException {

        List<String> output = Files.readAllLines(Paths.get("data/output/abc.txt"));
        StringBuilder result = new StringBuilder();
        System.out.println(output);

        output.add(fileName);
        System.out.println(fileName);
        for (int n = 0; n < output.size(); n++) {
            result.append(output.get(n) + "\n");
        }
        //result.append(fileName);
        System.out.println(result.toString());
        return result.toString();
    }
}
