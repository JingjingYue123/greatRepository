package com.djcps.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by TruthBean on 2017-08-08 21:51.
 */
public class StringUtils {
    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     */
    public static boolean isChinaPhoneLegal(String str) {
        try {
            String regExp = "^((13[0-9])|(14[57])|(15[0-9])|(17[0-8])|(18[0-9]))\\d{8}$";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(str);
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) {
//        String phone = "18767161212";
//        System.out.println(!isChinaPhoneLegal(phone));
//    }

    public static boolean isNumeric(String str) {
        try {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);
            return isNum.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 格式化时间
     * @param timestamp
     * @return
     */
    public static String formatDateStr(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            Date date = new Date(timestamp);
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
