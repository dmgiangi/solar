package dev.dmgiangi.solar.input.digital;

import dev.dmgiangi.solar.error.SolarException;
import dev.dmgiangi.solar.output.DigitalState;
import dev.dmgiangi.solar.output.GpioResourcesAccessException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DigitalInputReader {
    private final static String FILE_PATH_TEMPLATE = "/sys/class/gpio/gpio%d/value";
    private final List<DigitalInput> inputs;
    private final Duration flickeringDelta;
    private final Map<Integer, DigitalState> lastPinStatuses = new HashMap<>();
    private final Map<Integer, LocalDateTime> lastPinUpdates = new HashMap<>();

    public DigitalInputReader(
            List<DigitalInput> inputs,
            Duration flickeringDelta) {
        this.inputs = inputs;
        this.flickeringDelta = flickeringDelta;
    }

    @Async
    @Scheduled(initialDelay = 500, fixedDelay = 10)
    public void read() {
        for (DigitalInput input : inputs) {
            final int pinNumber = input.getPinNumber();
            if (weAreInFlickeringDelta(pinNumber))
                continue;

            try {
                final DigitalState state = readPin(pinNumber);
                final DigitalState lastReadState = this.lastPinStatuses.get(pinNumber);

                if (state.equals(lastReadState))
                    continue;

                this.lastPinStatuses.put(pinNumber, state);
                this.lastPinUpdates.put(pinNumber, LocalDateTime.now());
                triggerAction(input, state);
            } catch (SolarException e) {
                input.notifyError(e);
            }
        }
    }

    private boolean weAreInFlickeringDelta(int pinNumber) {
        final LocalDateTime lastUpdateTimestamp = this.lastPinUpdates.get(pinNumber);

        if (lastUpdateTimestamp == null)
            return false;

        return lastUpdateTimestamp.isAfter(LocalDateTime.now().minus(flickeringDelta));
    }

    @Async
    protected void triggerAction(DigitalInput input, DigitalState state) {
        switch (state) {
            case ON:
                input.onTrigger();
                break;
            case OFF:
                input.offTrigger();
                break;
        }
    }

    @NonNull
    private DigitalState readPin(int pinNumber) {
        final String filePath = String.format(FILE_PATH_TEMPLATE, pinNumber);
        try {
            final Path path = Path.of(filePath);
            final String value = Files.readString(path);

            if (value != null && value.startsWith("0"))
                return DigitalState.ON;
            else
                return DigitalState.OFF;
        } catch (IOException e) {
            e.printStackTrace();
            throw new GpioResourcesAccessException("", e);
        }
    }
}
