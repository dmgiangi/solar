package dev.dmgiangi.solar.output;

public interface OutputProvider {

    void set(DigitalOutput digitalOutput, DigitalState state);

    void init(DigitalOutput digitalOutput);
}
