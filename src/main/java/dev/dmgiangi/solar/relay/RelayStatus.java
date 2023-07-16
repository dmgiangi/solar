package dev.dmgiangi.solar.relay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelayStatus {
    private String name;
    private String status;
    private boolean enabled;
}
