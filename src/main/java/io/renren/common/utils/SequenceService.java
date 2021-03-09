package io.renren.common.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author allan
 */
@Service
public class SequenceService {
    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    public String getID(ID_Prefix prefix) {
        StringBuilder sb = new StringBuilder();
        switch (prefix) {
            case NULL:
                return "";
            default:
                sb.append(prefix.getValue());
                sb.append(new Date().getTime());
                sb.append(getSuffix(prefix.name()));
                return sb.toString();
        }
    }

    private String getSuffix(String key) {
        StringBuilder seq = new StringBuilder(getSequence(key).toString());
        while (seq.length() < 4) {
            seq.insert(0, "0");
        }
        return seq.toString();
    }

    public Long getSequence(String key) {
        return getSequence(key, 1, 1);
    }

    public Long getSequence(String key, int increment) {
        return getSequence(key, increment, 1);
    }

    public Long getSequence(String key, int increment, long expire) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        counter.getAndAdd(increment);
        if (expire > 0) {
            counter.expire(expire, TimeUnit.SECONDS);
        }
        return counter.longValue();
    }

    public enum ID_Prefix {
        NULL(""),
        ORDER("WX");

        private final String value;

        ID_Prefix(String value) {
            this.value = value;
        }

        public static ID_Prefix getTablePrefix(String value) {
            for (ID_Prefix status : ID_Prefix.values()) {
                if (status.toString().equalsIgnoreCase(value)) {
                    return status;
                }
            }
            return ID_Prefix.NULL;
        }

        public String getValue() {
            return this.value;
        }
    }
}
