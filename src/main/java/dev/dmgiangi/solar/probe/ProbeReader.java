package dev.dmgiangi.solar.probe;

import dev.dmgiangi.solar.probe.exception.ProbeChecksumException;
import dev.dmgiangi.solar.probe.exception.ProbeFileMalformedException;
import dev.dmgiangi.solar.probe.exception.ProbeNotFoundException;
import dev.dmgiangi.solar.probe.exception.ProbeValueException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ProbeReader {
    private static final String PATH_PATTERN = "/sys/bus/w1/devices/%s/w1_slave";

    public ProbeReader() {
    }

    public static Double getValue(Probe probe){
        String path = String.format(PATH_PATTERN, probe.getAddress());
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            if (lines.size() < 2)
                throw new ProbeFileMalformedException(probe);

            if (!lines.get(0).endsWith("YES"))
                throw new ProbeChecksumException(probe);

            final int index = lines.get(1).indexOf("t=");

            if (index == -1)
                throw new ProbeValueException(probe);

            final String valueString = lines.get(1).substring(index + 2);
            return Double.parseDouble(valueString) / 1000;
        } catch (IOException e) {
            throw new ProbeNotFoundException(probe);
        }
    }
}
