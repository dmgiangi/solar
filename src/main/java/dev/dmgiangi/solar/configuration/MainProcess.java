package dev.dmgiangi.solar.configuration;

import dev.dmgiangi.solar.probe.ProbesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@AllArgsConstructor
public class MainProcess {
    private final ProbesService service;

    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void task(){

    }
}
