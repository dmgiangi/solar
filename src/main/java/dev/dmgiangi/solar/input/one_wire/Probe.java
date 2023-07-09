package dev.dmgiangi.solar.input.one_wire;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Probe {
    private final String name;
    private final String address;
}
