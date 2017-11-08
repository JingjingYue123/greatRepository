package com.djcps.crm.integral.enums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TruthBean on 2017-08-09 21:07.
 */
public enum IntegralJournalEnum {
    ALL(0, "全部类型"),

    BUY(1, "购买产品送积分"),

    MALL_ORDER_CANCELED(2, "商城订单取消"),

    MALL_CONSUMPTION(3, "商城消费"),

    GROUP_ORDER_CANCELED(4, "团购订单取消"),

    COMMUNITY_ACTIVITY(5, "社区活动积分"),

    MALL_ACTIVITY(6, "商城活动积分"),

    PACKAGE_ACTIVITIES(7, "包辅活动积分"),

    MEMBER_REBATE(8, "会员返利积分"),

    EXCLUSIVE_REBATE(9, "专享返利积分"),

    OTHER_REBATE(10, "其他返利积分");

    public static Map<Integer, String> getIntegralJournalTypeList() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "全部类型");
        map.put(1, "购买产品送积分");
        map.put(2, "商城订单取消");
        map.put(3, "商城消费");
        map.put(4, "团购订单取消");
        map.put(5, "社区活动积分");
        map.put(6, "商城活动积分");
        map.put(7, "包辅活动积分");
        map.put(8, "会员返利积分");
        map.put(9, "专享返利积分");
        map.put(10, "其他返利积分");
        return map;
    }


    IntegralJournalEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private int code;

    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}