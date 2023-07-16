package dev.dmgiangi.solar.relay;

import dev.dmgiangi.solar.input.one_wire.ProbesService;
import dev.dmgiangi.solar.output.DigitalOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SolarPumpService implements RelayService {
    private final ProbesService probesService;

    private final DigitalOutput solarPump;
    public SolarPumpService(
            @Qualifier("solarPump") DigitalOutput solarPump,
            ProbesService probesService) {
        this.solarPump = solarPump;
        this.probesService = probesService;
    }

    @Override
    public void compute() {

    }


}
