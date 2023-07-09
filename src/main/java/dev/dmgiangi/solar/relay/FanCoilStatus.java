package dev.dmgiangi.solar.relay;

import java.util.Arrays;

public enum FanCoilStatus {
    OFF(0),
    ONE(1),
    TWO(2),
    THREE(3);

    private final int code;

    FanCoilStatus(int code) {
        this.code = code;
    }

    public static FanCoilStatus fromInt(int code) {
        return Arrays.stream(FanCoilStatus.values())
                .filter(status -> status.getCode() == code)
                .findFirst()
                .orElse(FanCoilStatus.OFF);
    }

    public int getCode() {
        return code;
    }
}
