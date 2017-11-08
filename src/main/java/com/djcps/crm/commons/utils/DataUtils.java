package com.djcps.crm.commons.utils;

import com.djcps.crm.finance.service.RechargeOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by L-wenbin on 2017/9/12.
 */
public class DataUtils {

    private static final Logger logger= LoggerFactory.getLogger(RechargeOrderService.class);
    private DataUtils(){}
    public static String replaceStr(String data){
        if(data == null) return data;
        return data.replace("@","\\"+"@")
                .replace(" ","")
                .replace("_","\\"+"_")
                .replace("%","\\"+"%");
    }

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 拿到当前月份加最近三个月之前的时间
     * @return
     */
    public static long getThreeMonthsEarly(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,year);
        instance.set(Calendar.MONTH,month);
        instance.add(Calendar.MONTH,-3);
        return instance.getTime().getTime();
    }

    /**
     * 校验搜索时间
     * @param start
     * @param end
     * @return
     */
    public static boolean checkSearchDate(String start,String end) throws ParseException {
        if (start!=null&&end!=null){
            System.out.println("开始时间："+start);
            logger.debug("时间校验-开始时间："+start+"         时间校验-结束时间："+end);
            System.out.println("结束时间："+end);
            long starttime = format.parse(start).getTime();
            long endtime = format.parse(end).getTime();
            return  checkStartTime(start) && starttime <= endtime?true:false;
        }
        if (start!=null&&end==null){
            return checkStartTime(start);
        }
        return true;
    }

    /**
     * 校验开始时间
     * @return
     * @param startTime
     * @throws ParseException
     */
    public static boolean checkStartTime(String startTime) throws ParseException {
        logger.debug("时间校验-开始时间："+startTime+"        三个月前的时间："+format.format(new Date(getThreeMonthsEarly())));
        long starttime = format.parse(startTime).getTime();
        return DataUtils.getThreeMonthsEarly() <= starttime?true:false;
    }

}
