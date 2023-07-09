package dev.dmgiangi.solar.input.one_wire;

public class MockProbeReader implements ProbeReader{
    public MockProbeReader() {
    }

    public Double getValue(Probe probe) {
        return 25.0;
    }
}
