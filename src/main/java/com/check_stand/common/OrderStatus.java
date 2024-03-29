package com.check_stand.common;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public enum OrderStatus {

    PAYING(1, "待支付"), OK(2, "完成");

    private final int flag;
    private final String desc;

    OrderStatus(int flag, String desc) {
        this.flag = flag;
        this.desc = desc;
    }

    public static OrderStatus valueOf(int flag) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.flag == flag) {
                return orderStatus;
            }
        }
        throw new RuntimeException("Not found flag = " + flag +
                " in OrderStatus");
    }
}
