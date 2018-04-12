package org.yyf.mybatisexmaple.plugin;

public enum ComputerState implements CodeInsideEnum {
    OPEN(10),         //开启
    CLOSE(11),         //关闭
    OFF_LINE(12),     //离线
    FAULT(200),     //故障
    UNKNOWN(255);     //未知

    private int code;
    ComputerState(int code) { this.code = code; }

    @Override
    public int getCode() { return this.code; }
}