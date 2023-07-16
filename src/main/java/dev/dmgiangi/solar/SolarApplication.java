package dev.dmgiangi.solar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableRedisRepositories
@SpringBootApplication
public class SolarApplication {
	public static void main(String[] args) {
		SpringApplication.run(SolarApplication.class, args);
	}
}
