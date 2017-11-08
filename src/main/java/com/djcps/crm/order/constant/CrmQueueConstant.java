package com.djcps.crm.order.constant;

/**
 * CRM消息队列 常量
 * @author lancher
 * @since 2017/8/21 10:07.
 */
public class CrmQueueConstant {

    //消息交换器
    public static String EXCHANG_CRM = "exchangeCrm";

    //消息匹配键 修改金额
    public static String ROUTING_BALANCE_CRM_KEY = "queueUpdateBalanceCrmKey";

    //消息匹配键 修改积分
    public static String ROUTING_INTEGRAL_CRM_KEY ="queueUpdateIntegralCrmKey";

    //消息匹配键 修改产品数
    public static String ROUTING_PRODUCT_CRM_KEY ="queueUpdateProductCrmKey";
}
