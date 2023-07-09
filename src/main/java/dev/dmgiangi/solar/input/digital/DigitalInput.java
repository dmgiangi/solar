package dev.dmgiangi.solar.input.digital;

import dev.dmgiangi.solar.error.SolarException;

public interface DigitalInput {
    String getName();

    int getPinNumber();

    void onTrigger();

    void offTrigger();

    void notifyError(SolarException solarException);
}