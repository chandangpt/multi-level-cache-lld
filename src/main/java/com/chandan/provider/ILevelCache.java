package com.chandan.provider;

import com.chandan.model.ReadResponse;
import com.chandan.model.WriteResponse;

import java.util.List;

public interface ILevelCache<Key, Value> {
    WriteResponse set(Key key, Value value);
    ReadResponse<Value> get(Key key);
    List<Double> getUsages();
}
