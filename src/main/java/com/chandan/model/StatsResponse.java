package com.chandan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class StatsResponse {
    private final Double avgReadTime;
    private final Double avgWriteTime;
    private final List<Double> usages;
}
