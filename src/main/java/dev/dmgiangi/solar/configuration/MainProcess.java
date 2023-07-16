package dev.dmgiangi.solar.configuration;

import dev.dmgiangi.solar.relay.RelayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
@Configuration
@AllArgsConstructor
public class MainProcess {
    private final List<RelayService> relayServices;

    @Scheduled(initialDelay = 1000, fixedDelay = 500)
    public void task() {
        relayServices.forEach(RelayService::compute);
    }
}
