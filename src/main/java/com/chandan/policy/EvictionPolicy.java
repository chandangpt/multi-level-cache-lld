package com.chandan.policy;

public interface EvictionPolicy<Key> {
    void keyAccessed(Key key);
    Key evictKey();
}
