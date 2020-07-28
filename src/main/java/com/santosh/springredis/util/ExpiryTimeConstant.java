package com.santosh.springredis.util;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public interface ExpiryTimeConstant {

    Map<String, Duration> EXPIRY = new HashMap<String, Duration>() {{
        put(Time.ONE_MIN, Duration.ofMinutes(1));
        put(Time.FIVE_MIN, Duration.ofMinutes(5));
        put(Time.ETERNAL, Duration.ofSeconds(-1));
    }};

    interface Time {
        String ONE_MIN = "ONE_MIN";
        String FIVE_MIN = "FIVE_MIN";
        String ETERNAL = "ETERNAL";
    }
}
