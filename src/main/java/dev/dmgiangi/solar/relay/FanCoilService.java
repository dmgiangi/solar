package dev.dmgiangi.solar.relay;

import com.pi4j.io.gpio.digital.DigitalOutput;
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
                fanCoil2.high();
                fanCoil3.high();
                fanCoil1.low();
                break;
            case TWO:
                fanCoil1.high();
                fanCoil3.high();
                fanCoil2.low();
                break;
            case THREE:
                fanCoil2.high();
                fanCoil1.high();
                fanCoil3.low();
                break;
            case OFF:
                fanCoil2.high();
                fanCoil3.high();
                fanCoil1.high();
        }
    }
}
