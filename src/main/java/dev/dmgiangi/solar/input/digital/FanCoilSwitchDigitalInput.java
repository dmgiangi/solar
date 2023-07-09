package dev.dmgiangi.solar.input.digital;

import dev.dmgiangi.solar.error.SolarException;
import dev.dmgiangi.solar.notification.NotificationService;
import dev.dmgiangi.solar.relay.FanCoilService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class FanCoilSwitchDigitalInput implements DigitalInput {
    private final String name;
    private final int pinNumber;
    private final NotificationService notificationService;
    private final FanCoilService fanCoilService;


    @Override
    public void onTrigger() {
        log.trace("ON");
        fanCoilService.step();
    }

    @Override
    public void offTrigger() {
        log.trace("OFF");
    }

    @Override
    public void notifyError(SolarException solarException) {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPinNumber() {
        return pinNumber;
    }
}
