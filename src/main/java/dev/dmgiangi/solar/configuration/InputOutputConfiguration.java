package dev.dmgiangi.solar.configuration;

import dev.dmgiangi.solar.input.digital.DigitalInput;
import dev.dmgiangi.solar.input.digital.DigitalInputReader;
import dev.dmgiangi.solar.input.digital.FanCoilSwitchDigitalInput;
import dev.dmgiangi.solar.input.one_wire.FileSystemProbeReader;
import dev.dmgiangi.solar.input.one_wire.MockProbeReader;
import dev.dmgiangi.solar.input.one_wire.Probe;
import dev.dmgiangi.solar.input.one_wire.ProbeReader;
import dev.dmgiangi.solar.notification.NotificationService;
import dev.dmgiangi.solar.output.*;
import dev.dmgiangi.solar.relay.FanCoilService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Duration;
import java.util.List;

@Configuration
public class InputOutputConfiguration {
    private static DigitalOutput getDigitalOutput(
            OutputProvider provider,
            String name,
            int pinNumber) {
        return new DigitalOutput(
                name,
                pinNumber,
                provider,
                DigitalState.OFF,
                DigitalState.OFF,
                false
        );
    }

    @Bean
    public DigitalInput digitalInput(
            NotificationService notificationService,
            FanCoilService fanCoilService,
            @Value("${input.digital.fan-coil-switch}") int pinNumber) {
        return new FanCoilSwitchDigitalInput("FAN COIL SWITCH", pinNumber, notificationService, fanCoilService);
    }

    @Bean
    public DigitalInputReader digitalInputReader(List<DigitalInput> digitalInputs) {
        return new DigitalInputReader(
                digitalInputs,
                Duration.ofMillis(40));
    }

    @Bean
    @Profile("default")
    public OutputProvider productionProvider() {
        return new FileSystemOutputProvider();
    }

    @Bean
    @Profile("dev")
    public OutputProvider devProvider() {
        return new ConsoleOutputProvider();
    }

    @Bean
    public DigitalOutput fanCoil1(OutputProvider provider,
                                  @Value("${output.digital.pin.fan-coil-1}") int pinNumber) {
        return getDigitalOutput(provider, "FAN COIL 1", pinNumber);
    }

    @Bean
    public DigitalOutput fanCoil2(OutputProvider provider,
                                  @Value("${output.digital.pin.fan-coil-2}") int pinNumber) {
        return getDigitalOutput(provider, "FAN COIL 2", pinNumber);
    }

    @Bean
    public DigitalOutput fanCoil3(OutputProvider provider,
                                  @Value("${output.digital.pin.fan-coil-3}") int pinNumber) {
        return getDigitalOutput(provider, "FAN COIL 3", pinNumber);
    }

    @Bean
    public DigitalOutput thermoValve(OutputProvider provider,
                                     @Value("${output.digital.pin.thermo-valve}") int pinNumber) {
        return getDigitalOutput(provider, "THERMO VALVE", pinNumber);
    }

    @Bean
    public DigitalOutput solarPump(OutputProvider provider,
                                   @Value("${output.digital.pin.solar-pump}") int pinNumber) {
        return getDigitalOutput(provider, "SOLAR PUMP", pinNumber);
    }

    @Bean
    public DigitalOutput thermoIntegration(OutputProvider provider,
                                           @Value("${output.digital.pin.thermo-integration}") int pinNumber) {
        return getDigitalOutput(provider, "THERMO INTEGRATION", pinNumber);
    }

    @Bean
    public DigitalOutput unused1(OutputProvider provider,
                                 @Value("${output.digital.pin.unused-1}") int pinNumber) {
        return getDigitalOutput(provider, "UNUSED 1", pinNumber);
    }

    @Bean
    public DigitalOutput unused2(OutputProvider provider,
                                 @Value("${output.digital.pin.unused-2}") int pinNumber) {
        return getDigitalOutput(provider, "UNUSED 2", pinNumber);
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
    public Probe solarIn(@Value("${input.one-wire.addresses.solar-in}") String solarIn) {
        return new Probe("SOLAR IN", solarIn);
    }

    @Bean
    public Probe solarOut(@Value("${input.one-wire.addresses.solar-out}") String solarOut) {
        return new Probe("SOLAR OUT", solarOut);
    }

    @Bean
    public Probe thermoFirePlace(@Value("${input.one-wire.addresses.thermo-fire-place}") String thermoFirePlace) {
        return new Probe("THERMO FIRE PLACE", thermoFirePlace);
    }

    @Bean
    public Probe boilerUp(@Value("${input.one-wire.addresses.boiler-up}") String boilerUp) {
        return new Probe("BOILER UP", boilerUp);
    }

    @Bean
    public Probe boilerMiddle(@Value("${input.one-wire.addresses.boiler-middle}") String boilerMiddle) {
        return new Probe("BOILER MIDDLE", boilerMiddle);
    }

    @Bean
    public Probe boilerLow(@Value("${input.one-wire.addresses.boiler-low}") String boilerLow) {
        return new Probe("BOILER LOW", boilerLow);
    }
}
