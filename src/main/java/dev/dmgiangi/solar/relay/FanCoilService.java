package dev.dmgiangi.solar.relay;

import dev.dmgiangi.solar.output.DigitalOutput;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FanCoilService {
    private final DigitalOutput fanCoil1;
    private final DigitalOutput fanCoil2;
    private final DigitalOutput fanCoil3;

    public FanCoilService(
            @Qualifier("fanCoil1") DigitalOutput fanCoil1,
            @Qualifier("fanCoil2") DigitalOutput fanCoil2,
            @Qualifier("fanCoil3") DigitalOutput fanCoil3
    ) {
        this.fanCoil1 = fanCoil1;
        this.fanCoil2 = fanCoil2;
        this.fanCoil3 = fanCoil3;
    }

    public void setStatus(@NonNull FanCoilStatus status){
        switch (status){
            case ONE:
                fanCoil2.on();
                fanCoil3.on();
                fanCoil1.off();
                break;
            case TWO:
                fanCoil1.on();
                fanCoil3.on();
                fanCoil2.off();
                break;
            case THREE:
                fanCoil2.on();
                fanCoil1.on();
                fanCoil3.off();
                break;
            case OFF:
                fanCoil2.on();
                fanCoil3.on();
                fanCoil1.on();
        }
    }
}
