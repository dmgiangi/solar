package dev.dmgiangi.solar.status;

import dev.dmgiangi.solar.relay.FanCoilStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RedisStatusService {
    public static final String FAN_COIL_STATUS = "fanCoil:status";
    public static final String FAN_COIL_ENABLED = "fanCoil:enabled";
    public static final String CANNOT_SET = "Cannot set ";
    public static final String CANNOT_GET = "Cannot get ";

    private final RedisTemplate<String, String> redisTemplate;

    public FanCoilStatus getFanCoilStatus() {
        try {
            final String s = redisTemplate.opsForValue().get(FAN_COIL_STATUS);
            if (s == null)
                return FanCoilStatus.OFF;

            return FanCoilStatus.valueOf(s);
        } catch (Exception e) {
            log.error(CANNOT_GET + FAN_COIL_STATUS, e);

            return FanCoilStatus.OFF;
        }
    }

    public void setFanCoilStatus(FanCoilStatus status) {
        try {
            redisTemplate.opsForValue().set(FAN_COIL_STATUS, status.name());
        } catch (Exception e) {
            log.error(CANNOT_SET + FAN_COIL_STATUS, e);
        }

    }

    public boolean isFanCoilEnabled() {
        try {
            final String s = redisTemplate.opsForValue().get(FAN_COIL_ENABLED);
            if (s == null)
                return false;

            return Boolean.parseBoolean(s);
        } catch (Exception e) {
            log.error(CANNOT_GET + FAN_COIL_ENABLED, e);

            return false;
        }
    }

    public void setFanCoilEnabled(Boolean enabled) {
        try {
            redisTemplate.opsForValue().set(FAN_COIL_ENABLED, enabled.toString());
        } catch (Exception e) {
            log.error(CANNOT_SET + FAN_COIL_ENABLED, e);
        }

    }
}
