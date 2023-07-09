package dev.dmgiangi.solar.output;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FileSystemOutputProvider implements OutputProvider {
    private static final String ROOT = "/sys/class/gpio/";
    private static final String EXPORT = ROOT + "export";
    private static final String PIN_FOLDER = ROOT + "gpio%d";
    private static final String ACTIVE_LOW = "/active_low";
    private static final String VALUE = "/value";
    private static final String DIRECTION = "/direction";
    private static final Map<Integer, String> endStatuses = new HashMap<>();
    public static final String OUT = "out";

    @Override
    public void set(DigitalOutput digitalOutput, OutputState state) {
        final int pinNumber = digitalOutput.getPinNumber();
        final String path = String.format(PIN_FOLDER + VALUE, pinNumber);
        final String status = mapOutputState(digitalOutput.isActiveLow(), state);
        write(path, status);
    }

    @Override
    public void init(DigitalOutput digitalOutput) {
        boolean isActiveLow = digitalOutput.isActiveLow();
        final int pinNumber = digitalOutput.getPinNumber();
        final String endState = mapOutputState(isActiveLow, digitalOutput.getEndState());

        endStatuses.put(pinNumber, endState);

        if (pinIsNotActive(pinNumber))
            write(EXPORT, String.valueOf(pinNumber));

        final String pinFolder = String.format(PIN_FOLDER, pinNumber);

        final String actualDirection = read(pinFolder + DIRECTION);
        if(notStartWith(actualDirection, OUT))
            write(pinFolder + DIRECTION, OUT);

        final String desiredActiveLowValue = mapActiveLow(isActiveLow);
        final String actualActiveLowValue = read(pinFolder + ACTIVE_LOW);
        if (notStartWith(actualActiveLowValue, desiredActiveLowValue))
            write(pinFolder + ACTIVE_LOW, desiredActiveLowValue);

        final String desiredState = mapOutputState(isActiveLow, digitalOutput.getInitState());
        final String actualState = read(pinFolder + VALUE);
        if(notStartWith(actualState, desiredState))
            write(pinFolder + VALUE, desiredState);
    }

    private boolean notStartWith(String string, String match) {
        return string == null
                || !string.startsWith(match);
    }

    private String mapOutputState(boolean activeLow, OutputState state) {
        final boolean mappedState = (activeLow && OutputState.OFF.equals(state))
                || (!activeLow && OutputState.ON.equals(state));

        final String isActiveLow = activeLow ? "LOW" : "HIGH";

        log.info("Mapping Output State digital output is active {} the required state is {} the computed value is {}",
                isActiveLow,
                state,
                mappedState);

        return mappedState
                ? "1"
                : "0";
    }

    private String mapActiveLow(boolean isActiveLow) {
        return isActiveLow ? "0" : "1";
    }

    @PreDestroy
    public void setEndStatus() {
        endStatuses
                .forEach((key, value) -> write(
                        String.format(PIN_FOLDER + VALUE, key),
                        value));
    }

    private void write(String file, String value) {
        try {
            Path path = Path.of(file);
            log.info("WRITING ON {} value {}", file, value);
            Files.write(path, value.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GpioResourcesAccssException(e);
        }
    }

    private boolean pinIsNotActive(int pinNumber) {
        final String pinFolder = String.format(PIN_FOLDER, pinNumber);
        try {
            final Path path = Paths.get(pinFolder);
            return !Files.exists(path);
        } catch (Exception e) {
            return true;
        }
    }

    private String read(String file) {
        try {
            Path path = Path.of(file);
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GpioResourcesAccssException(e);
        }
    }
}
