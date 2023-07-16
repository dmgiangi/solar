package dev.dmgiangi.solar.input.one_wire;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProbeStatus {
    private String name;
    private Double temp;
}
