package com.example.integration;

import org.springframework.integration.file.DefaultFileNameGenerator;
import org.springframework.messaging.Message;

public class NewFileGenerator extends DefaultFileNameGenerator {

    public NewFileGenerator() {
        super();
    }

    @Override
    public String generateFileName(Message<?> message) {
        return "abc.txt";
    }
}
