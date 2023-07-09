package dev.dmgiangi.solar.configuration;

import dev.dmgiangi.solar.output.*;
import dev.dmgiangi.solar.probe.FileSystemProbeReader;
import dev.dmgiangi.solar.probe.MockProbeReader;
import dev.dmgiangi.solar.probe.Probe;
import dev.dmgiangi.solar.probe.ProbeReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class Pi4jConfiguration {
    @Bean
    @Profile("default")
    public OutputProvider productionProvider(){
        return new FileSystemOutputProvider();
    }

    @Bean
    @Profile("dev")
    public OutputProvider devProvider(){
        return new ConsoleOutputProvider();
    }

    @Bean
    public DigitalOutput fanCoil1(OutputProvider provider,
                                  @Value("${relays.pin.fan-coil-1}") int pinNumber) {
        return getDigitalOutput(provider, "FAN COIL 1", pinNumber);
    }

    @Bean
    public DigitalOutput fanCoil2(OutputProvider provider,
                                  @Value("${relays.pin.fan-coil-2}") int pinNumber) {
        return getDigitalOutput(provider, "FAN COIL 2", pinNumber);
    }

    @Bean
    public DigitalOutput fanCoil3(OutputProvider provider,
                                  @Value("${relays.pin.fan-coil-3}") int pinNumber) {
        return getDigitalOutput(provider, "FAN COIL 3", pinNumber);
    }

    @Bean
    public DigitalOutput thermoValve(OutputProvider provider,
                                     @Value("${relays.pin.thermo-valve}") int pinNumber) {
        return getDigitalOutput(provider, "THERMO VALVE", pinNumber);
    }

    @Bean
    public DigitalOutput solarPump(OutputProvider provider,
                                   @Value("${relays.pin.solar-pump}") int pinNumber) {
        return getDigitalOutput(provider, "SOLAR PUMP", pinNumber);
    }

    @Bean
    public DigitalOutput thermoIntegration(OutputProvider provider,
                                           @Value("${relays.pin.thermo-integration}") int pinNumber) {
        return getDigitalOutput(provider, "THERMO INTEGRATION", pinNumber);
    }

    @Bean
    public DigitalOutput unused1(OutputProvider provider,
                                 @Value("${relays.pin.unused-1}") int pinNumber) {
        return getDigitalOutput(provider, "UNUSED 1", pinNumber);
    }

    @Bean
    public DigitalOutput unused2(OutputProvider provider,
                                 @Value("${relays.pin.unused-2}") int pinNumber) {
        return getDigitalOutput(provider, "UNUSED 2", pinNumber);
    }

    private static DigitalOutput getDigitalOutput(
            OutputProvider provider,
            String name,
            int pinNumber) {
        return new DigitalOutput(
                name,
                pinNumber,
                provider,
                OutputState.OFF,
                OutputState.OFF,
                true
        );
    }

    @Bean
    @Profile("default")
    public ProbeReader productionProbeReader(){
        return new FileSystemProbeReader();
    }

    @Bean
    @Profile("dev")
    public ProbeReader devProbeReader(){
        return new MockProbeReader();
    }

    @Bean
    public Probe solarIn(@Value("${probes.address.solar-in}") String solarIn) {
        return new Probe("SOLAR IN", solarIn);
    }

    @Bean
    public Probe solarOut(@Value("${probes.address.solar-out}") String solarOut) {
        return new Probe("SOLAR OUT", solarOut);
    }

    @Bean
    public Probe thermoFirePlace(@Value("${probes.address.thermo-fire-place}") String thermoFirePlace) {
        return new Probe("THERMO FIRE PLACE", thermoFirePlace);
    }

    @Bean
    public Probe boilerUp(@Value("${probes.address.boiler-up}") String boilerUp) {
        return new Probe("BOILER UP", boilerUp);
    }

    @Bean
    public Probe boilerMiddle(@Value("${probes.address.boiler-middle}") String boilerMiddle) {
        return new Probe("BOILER MIDDLE", boilerMiddle);
    }

    @Bean
    public Probe boilerLow(@Value("${probes.address.boiler-low}") String boilerLow) {
        return new Probe("BOILER LOW", boilerLow);
    }
}
