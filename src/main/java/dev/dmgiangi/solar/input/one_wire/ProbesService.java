package dev.dmgiangi.solar.input.one_wire;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProbesService {
    private final ProbeDecoupler decoupler;

    private final Probe solarIn;
    private final Probe solarOut;
    private final Probe thermoFirePlace;
    private final Probe boilerUp;
    private final Probe boilerMiddle;
    private final Probe boilerLow;
    private final List<Probe> probes;

    public ProbesService(
            @Qualifier("solarIn") Probe solarIn,
            @Qualifier("solarOut") Probe solarOut,
            @Qualifier("thermoFirePlace") Probe thermoFirePlace,
            @Qualifier("boilerUp") Probe boilerUp,
            @Qualifier("boilerMiddle") Probe boilerMiddle,
            @Qualifier("boilerLow") Probe boilerLow,
            ProbeDecoupler decoupler) {
        this.solarIn = solarIn;
        this.solarOut = solarOut;
        this.thermoFirePlace = thermoFirePlace;
        this.boilerUp = boilerUp;
        this.boilerMiddle = boilerMiddle;
        this.boilerLow = boilerLow;
        this.decoupler = decoupler;
        this.probes = List.of(solarIn, solarOut, thermoFirePlace, boilerUp, boilerMiddle, boilerLow);
    }

    public Double getSolarIn() {
        return getProbeValue(solarIn);
    }

    public Double getSolarOut() {
        return getProbeValue(solarOut);
    }

    public Double getThermoFirePlace() {
        return getProbeValue(thermoFirePlace);
    }

    public Double getBoilerUp() {
        return getProbeValue(boilerUp);
    }

    public Double getBoilerMiddle() {
        return getProbeValue(boilerMiddle);
    }

    public Double getBoilerLow() {
        return getProbeValue(boilerLow);
    }

    @Nullable
    private Double getProbeValue(@NonNull Probe probe) {
        return decoupler.getValue(probe);
    }

    public List<ProbeStatus> getProbeStatuses() {
        return probes
                .stream()
                .map(probe -> new ProbeStatus(probe.getName(), getProbeValue(probe)))
                .collect(Collectors.toList());
    }
}
