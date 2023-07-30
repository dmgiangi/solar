package dev.dmgiangi.solar.controller;

import dev.dmgiangi.solar.input.one_wire.ProbeStatus;
import dev.dmgiangi.solar.input.one_wire.ProbesService;
import dev.dmgiangi.solar.relay.RelayService;
import dev.dmgiangi.solar.relay.RelayStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class StatusController {
    private final List<RelayService> relayServices;
    private final ProbesService probesService;

    @CrossOrigin(origins = "*")
    @GetMapping(value = "status", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Snapshot> getSnapshot() {
        return Flux
                .interval(Duration.ofSeconds(2))
                .map(this::getSnapshotInternal);
    }

    private Snapshot getSnapshotInternal(Long t) {
        List<RelayStatus> relayStatuses = relayServices
                .stream()
                .map(RelayService::getStatus)
                .collect(Collectors.toList());

        List<ProbeStatus> probeStatuses = probesService.getProbeStatuses();

        return new Snapshot(relayStatuses, probeStatuses);
    }
}
