package com.ssafy.trend_gaza.attraction.entity;

public class Sido {

    private Integer sidoCode;
    private String sidoName;

    public Integer getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(Integer sidoCode) {
        this.sidoCode = sidoCode;
    }

    public String getSidoName() {
        return sidoName;
    }

    public void setSidoName(String sidoName) {
        this.sidoName = sidoName;
    }

    @Override
    public String toString() {
        return "SidoDto [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
    }
}
