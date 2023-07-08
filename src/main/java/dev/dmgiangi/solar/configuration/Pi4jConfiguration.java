package dev.dmgiangi.solar.configuration;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.IOType;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalOutputProvider;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.platform.Platform;
import dev.dmgiangi.solar.probe.Probe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pi4jConfiguration {
    @Bean
    public Context pi4j() {
        return Pi4J.newAutoContext();
    }

    @Bean
    public Platform platform(Context context) {
        return context.platforms().getDefault();
    }

    @Bean
    public DigitalOutputProvider digitalOutputProvider(Platform platform) {
        return platform.provider(IOType.DIGITAL_OUTPUT);
    }

    @Bean
    public DigitalOutput fanCoil1(Context context,
                                  @Value("${relays.pin.fan-coil-1}") int pinNumber) {
        return getDigitalOutput(context, "FAN COIL 1", pinNumber);
    }

    @Bean
    public DigitalOutput fanCoil2(Context context,
                                  @Value("${relays.pin.fan-coil-2}") int pinNumber) {
        return getDigitalOutput(context, "FAN COIL 2", pinNumber);
    }

    @Bean
    public DigitalOutput fanCoil3(Context context,
                                  @Value("${relays.pin.fan-coil-3}") int pinNumber) {
        return getDigitalOutput(context, "FAN COIL 3", pinNumber);
    }

    @Bean
    public DigitalOutput thermoValve(Context context,
                                     @Value("${relays.pin.thermo-valve}") int pinNumber) {
        return getDigitalOutput(context, "THERMO VALVE", pinNumber);
    }

    @Bean
    public DigitalOutput solarPump(Context context,
                                   @Value("${relays.pin.solar-pump}") int pinNumber) {
        return getDigitalOutput(context, "SOLAR PUMP", pinNumber);
    }

    @Bean
    public DigitalOutput thermoIntegration(Context context,
                                           @Value("${relays.pin.thermo-integration}") int pinNumber) {
        return getDigitalOutput(context, "THERMO INTEGRATION", pinNumber);
    }

    @Bean
    public DigitalOutput unused1(Context context,
                                 @Value("${relays.pin.unused-1}") int pinNumber) {
        return getDigitalOutput(context, "UNUSED 1", pinNumber);
    }

    @Bean
    public DigitalOutput unused2(Context context,
                                 @Value("${relays.pin.unused-2}") int pinNumber) {
        return getDigitalOutput(context, "UNUSED 2", pinNumber);
    }

    private static DigitalOutput getDigitalOutput(Context context,
                                                  String name,
                                                  int pinNumber) {
        DigitalOutputConfig config = DigitalOutput
                .newConfigBuilder(context)
                .name(name)
                .address(pinNumber)
                .shutdown(DigitalState.HIGH)
                .initial(DigitalState.HIGH)
                .provider("pigpio-digital-output")
                .build();

        return context.create(config);
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
