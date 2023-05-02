package com.chandan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ReadResponse<Value> {
    Value value;
    Double totalTime;
}
