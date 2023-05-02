package com.chandan.provider;

import com.chandan.model.LevelCacheData;
import com.chandan.model.ReadResponse;
import com.chandan.model.WriteResponse;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
@AllArgsConstructor
public class DefaultLevelCache<Key, Value> implements ILevelCache<Key, Value> {
    private final LevelCacheData levelCacheData;
    private final CacheProvider<Key, Value> cacheProvider;

    private final ILevelCache<Key, Value> next;
    @Override
    public WriteResponse set(Key key, Value value) {
        Double curTime = 0.0;
        Value curLevelValue = cacheProvider.get(key);
        curTime += levelCacheData.getReadTime();
        if(!value.equals(curLevelValue)) {
            cacheProvider.set(key, value);
            curTime += levelCacheData.getWriteTime();
        }
        curTime += next.set(key, value).getTimeTaken();
        return new WriteResponse(curTime);
    }

    @Override
    public ReadResponse<Value> get(Key key) {
        Double curTime = 0.0;
        Value curLevelValue = cacheProvider.get(key);
        curTime += levelCacheData.getReadTime();
        if(curLevelValue == null) {
            ReadResponse<Value> nextResponse = next.get(key);
            curTime += nextResponse.getTotalTime();
            curLevelValue = nextResponse.getValue();
            if(curLevelValue != null) {
                cacheProvider.set(key, curLevelValue);
                curTime += levelCacheData.getWriteTime();
            }
        }
        return new ReadResponse<>(curLevelValue, curTime);
    }

    @Override
    public List<Double> getUsages() {
        final List<Double> usages;
        if(next == null) {
            usages = Collections.emptyList();
        } else {
            usages = next.getUsages();
        }
        usages.add(0, cacheProvider.getCurrentUsage());
        return usages;
    }
}
