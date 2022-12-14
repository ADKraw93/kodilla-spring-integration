package com.example.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.DefaultFileNameGenerator;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    IntegrationFlow fileIntegrationFlow(
            FileReadingMessageSource fileAdapter,
            FileTransformer transformer,
            FileWritingMessageHandler outputFileHandler) {

        return IntegrationFlows.from(fileAdapter, config -> config.poller(Pollers.fixedDelay(1000)))
                .transform(transformer, "transformFile")
                .handle(outputFileHandler)
                .get();
    }

    @Bean
    FileReadingMessageSource fileAdapter() {
        FileReadingMessageSource fileSource = new FileReadingMessageSource();
        fileSource.setDirectory(new File("data/input"));

        return fileSource;
    }

    @Bean
    FileTransformer transformer() {
        return new FileTransformer();
    }

    @Bean
    FileWritingMessageHandler outputFileAdapter() {
        File directory = new File("data/output");
        FileWritingMessageHandler handler = new FileWritingMessageHandler(directory);
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setFileNameGenerator(generator());

        return handler;
    }

   /* @Bean
    public DefaultFileNameGenerator defaultFileNameGenerator() {
        DefaultFileNameGenerator defaultFileNameGenerator = new DefaultFileNameGenerator();
        defaultFileNameGenerator.setHeaderName("abc");
        //defaultFileNameGenerator.setExpression("headers['id']");
        return defaultFileNameGenerator;
    }*/

    @Bean
    public NewFileGenerator generator() {
        return new NewFileGenerator();
    }
}
