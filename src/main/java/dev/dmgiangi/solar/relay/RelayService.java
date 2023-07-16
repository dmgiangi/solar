package dev.dmgiangi.solar.relay;

public interface RelayService {
    void compute();

    RelayStatus getStatus();
}
