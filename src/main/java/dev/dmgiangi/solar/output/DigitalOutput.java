package dev.dmgiangi.solar.output;

import lombok.Getter;

@Getter
public class DigitalOutput {
    private final String name;
    private final int pinNumber;
    private final OutputProvider outputProvider;
    private final OutputState initState;
    private final OutputState endState;
    private final boolean activeLow;

    public void on() {
        outputProvider.set(this, OutputState.OFF);
    }

    public void off() {
        outputProvider.set(this, OutputState.OFF);
    }

    public DigitalOutput(
            String name,
            int pinNumber,
            OutputProvider outputProvider,
            OutputState initState,
            OutputState endState,
            boolean activeLow) {
        this.name = name;
        this.pinNumber = pinNumber;
        this.outputProvider = outputProvider;
        this.initState = initState;
        this.endState = endState;
        this.activeLow = activeLow;

        outputProvider.init(this);
    }
}
