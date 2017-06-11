package com.zxz.zhihudaliy.Bean;

/**
 * Created by suan on 2017/6/9.
 */

public enum DayNight {
//    枚举
    DAY("DAY",0),
    NIGHT("NIGHT",1);
    private String name;
    private int code;

    DayNight(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
