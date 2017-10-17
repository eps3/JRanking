package cn.sheep3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JRankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(JRankingApplication.class, args);
    }
}
