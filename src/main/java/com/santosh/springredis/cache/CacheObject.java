package com.santosh.springredis.cache;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CacheObject implements Serializable {
    private String id;
    private String name;

}
