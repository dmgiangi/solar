package dev.dmgiangi.solar.controller;

import dev.dmgiangi.solar.input.one_wire.ProbeStatus;
import dev.dmgiangi.solar.relay.RelayStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Snapshot {
    private List<RelayStatus> relayStatus;
    private List<ProbeStatus> probeStatuses;
}
