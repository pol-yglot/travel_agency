package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.mapper.user")
@Slf4j
public class DemoApplication implements CommandLineRunner {

    @Value("${server.port:8080}")
    private String port;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Override
    public void run(String... args) {
        String url = "http://localhost:" + port + contextPath;

        // 로고 출력
        log.info("\n" +
                " _____ ______   ___  ______ ______  _____  ______\n" +
                "|_   _|| ___ \\ / _ \\ | ___ \\| ___ \\|_   _||___  /\n" +
                "  | |  | |_/ // /_\\ \\| |_/ /| |_/ /  | |     / / \n" +
                "  | |  |    / |  _  ||  __/ | ___ \\  | |    / /  \n" +
                "  | |  | |\\ \\ | | | || |    | |_/ / _| |_ ./ /___\n" +
                "  \\_/  \\_| \\_|\\_| |_/\\_|    \\____/  \\___/ \\_____/\n" +
                "                                                 \n");
        log.info("✅ 접속: http://localhost:8081/");
    }
}
