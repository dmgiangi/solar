package dev.dmgiangi.solar.output;

public interface OutputProvider {

    void set(DigitalOutput digitalOutput, OutputState state);

    void init(DigitalOutput digitalOutput);
}
