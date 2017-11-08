package com.djcps.crm.productServer.Utils;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import org.apache.poi.ss.formula.functions.T;

import java.text.SimpleDateFormat;
import java.util.*;

import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import javax.validation.Validation;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;

/**
 * Created by ZhangMingHui on 2017/7/24.
 */
public class Utils {

    /**
     * 获取系统时间  默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param pattern
     * @return
     */
    public static Date getSysDate(String... param) {
        Date date = null;
        String pattern = "";
        if (param.length < 1) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        } else {
            pattern = param[0];
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//设置日期格式

        try {
            date = sdf.parse(sdf.format(new Date()));
            return date;
        } catch (Exception e) {
            return new Date();
        }

    }

    /**
     * 获取系统时间  默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param pattern
     * @return
     */
    public static String getSysTime(String... param) {
        Date date = null;
        String pattern = "";
        if (param.length < 1) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        } else {
            pattern = param[0];
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//设置日期格式
        return sdf.format(new Date());
    }

    /**
     * 根据String时间获取long时间
     *
     * @param pattern
     * @return
     */
    public static long getLongforStrDate(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            Date date = sdf.parse(time);
            return date.getTime();
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * String 空判断
     *
     * @param values
     * @return
     */
    public static boolean isEmpty(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return UUID.randomUUID().toString();
    }

    /**
     * 判断数组中是否有重复值
     * @return
     */
    public static boolean checkRepeat(String[] array) {
        Set<String> set = new HashSet<String>();
        for (String str : array) {
            set.add(str);
        }

        if (set.size() != array.length) {
            return true;//有重复
        } else {
            return false;//不重复
        }

    }

//    /**
//     * 检验参数
//     * @return
//     */
//    public static boolean complexParam(Object obj){
//        try {
//            obj.getClass().get;
//            ComplexResult ret = FluentValidator.checkAll().failFast()
//                    .on(obj, new HibernateValidator<>().Validator())
//                    .doValidate()
//                    .result(toComplex());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    /**
     *
     * @return
     */
    public static int getSunm(int number){
        int max=number*4/5;
        int min=max/4;
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }
}
