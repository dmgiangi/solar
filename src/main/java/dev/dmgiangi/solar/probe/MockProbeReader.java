package dev.dmgiangi.solar.probe;

import dev.dmgiangi.solar.probe.exception.ProbeChecksumException;
import dev.dmgiangi.solar.probe.exception.ProbeFileMalformedException;
import dev.dmgiangi.solar.probe.exception.ProbeNotFoundException;
import dev.dmgiangi.solar.probe.exception.ProbeValueException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MockProbeReader implements ProbeReader{
    public MockProbeReader() {
    }

    public Double getValue(Probe probe) {
        return 25.0;
    }
}
