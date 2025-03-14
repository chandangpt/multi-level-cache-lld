package com.chandan.storage;

import com.chandan.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage<Key, Value> implements Storage<Key, Value> {

    private final Map<Key, Value> storage;
    private final Integer capacity;

    public InMemoryStorage(Integer capacity) {
        this.storage = new HashMap<Key, Value>();
        this.capacity = capacity;
    }
    @Override
    public void add(Key key, Value value) throws StorageFullException {
        if(isStorageFull()) {
            throw new StorageFullException();
        }
        storage.put(key, value);
    }

    @Override
    public void remove(Key key) {
        storage.remove(key);
    }

    @Override
    public Value get(Key key) {
        return storage.get(key);
    }

    @Override
    public double getCurrentUsage() {
        return ((double)storage.size()) / capacity;
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }
}
