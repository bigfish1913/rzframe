package com.rz.frame.utils;

import redis.clients.jedis.Jedis;
import sun.swing.StringUIClientPropertyKey;

import java.time.LocalDateTime;
import java.util.List;

public class RedisUtils {
    static Jedis jedis = new Jedis("192.168.0.108");


    public static <T> T get(String key, Class<T> clazz) {
        try {
            String content = jedis.get(key);
            if (StringUtils.isEmpty(content)) {
                return null;
            }
            return JsonUtils.toBean(content, clazz);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String get(String key) {
        try {
            String content = jedis.get(key);
            if (StringUtils.isEmpty(content)) {
                return null;
            }
            return content;
        } catch (Exception ex) {
            return null;
        }
    }

    public static <T> List<T> getList(String key, Class<T> clazz) {
        try {
            String content = jedis.get(key);
            if (StringUtils.isEmpty(content)) {
                return null;
            }
            return JsonUtils.toArray(content, clazz);
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean set(String key, Object o) {
        try {
            if (o == null)
                return false;
            String content = jedis.set(key, JsonUtils.serializeObject(o));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //滑动过期
    public static boolean set(String key, Object o, int senconds) {
        try {
            boolean bSet = set(key, o);
            if (!bSet) {
                return false;
            }
            jedis.expire(key, senconds);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //绝对过期
    public static boolean set(String key, Object o, long unixTime) {
        try {
            boolean bSet = set(key, o);
            if (!bSet) {
                return false;
            }
            jedis.expireAt(key, unixTime);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //绝对过期
    public static boolean set(String key, Object o, LocalDateTime localDateTime) {
        try {
            boolean bSet = set(key, o);
            if (!bSet) {
                return false;
            }
            jedis.expireAt(key, DateUtils.locateTimeToUnixTime(localDateTime));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean setnx(String key, Object o, LocalDateTime localDateTime) {
        try {
            long setnx = jedis.setnx(key, JsonUtils.serializeObject(o));
            jedis.expireAt(key, DateUtils.locateTimeToUnixTime(localDateTime));
            if (setnx <= 0) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean setnx(String key, Object o, int senconds) {
        try {
            long setnx = jedis.setnx(key, JsonUtils.serializeObject(o));
            jedis.expire(key, senconds);
            if (setnx <= 0) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static long incr(String key) {
        try {
            return jedis.incr(key);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static long decr(String key) {
        try {
            return jedis.decr(key);
        } catch (Exception ex) {
            return 0;
        }
    }
}
