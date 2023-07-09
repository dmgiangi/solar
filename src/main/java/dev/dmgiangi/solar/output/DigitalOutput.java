package dev.dmgiangi.solar.output;

import lombok.Getter;

@Getter
public class DigitalOutput {
    private final String name;
    private final int pinNumber;
    private final OutputProvider outputProvider;
    private final DigitalState initState;
    private final DigitalState endState;
    private final boolean activeLow;

    public DigitalOutput(
            String name,
            int pinNumber,
            OutputProvider outputProvider,
            DigitalState initState,
            DigitalState endState,
            boolean activeLow) {
        this.name = name;
        this.pinNumber = pinNumber;
        this.outputProvider = outputProvider;
        this.initState = initState;
        this.endState = endState;
        this.activeLow = activeLow;

        outputProvider.init(this);
    }

    public void on() {
        outputProvider.set(this, DigitalState.ON);
    }

    public void off() {
        outputProvider.set(this, DigitalState.OFF);
    }
}
