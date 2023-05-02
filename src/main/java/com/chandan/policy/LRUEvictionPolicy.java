package com.chandan.policy;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {
    @Override
    public void keyAccessed(Key key) {

    }

    @Override
    public Key evictKey() {
        return null;
    }
}
