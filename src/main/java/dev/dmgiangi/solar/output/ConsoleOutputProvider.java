package dev.dmgiangi.solar.output;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleOutputProvider implements OutputProvider {
    @Override
    public void set(DigitalOutput digitalOutput, OutputState state) {
        log.info("Pin {} number [{}] set as {}", digitalOutput.getName(), digitalOutput.getPinNumber(), state);
    }

    @Override
    public void init(DigitalOutput digitalOutput) {

    }
}
