package com.djcps.crm.finance.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.commons.utils.DataUtils;
import com.djcps.crm.commons.utils.HibernateValidator;
import com.djcps.crm.finance.enums.ReportFormsEnum;
import com.djcps.crm.finance.model.ListModel;
import com.djcps.crm.finance.service.ReportFormsService;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;
import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;

import static com.djcps.crm.finance.enums.RechargeOrderEnum.SYS_ERROR;

/**
 * Created by jmb on 2017/9/9.
 */
@RestController
public class ReportFormsController {
    private static final Logger logger = LoggerFactory.getLogger(RechargeOrderController.class);
    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();

    @Autowired
    private ReportFormsService reportFormsService;


    /**
     * 该接口用于查询混合报表列表
     * @param json 分条件查询请求参数
     * @return 销售报表列表
     */
    @RequestMapping(name = "该接口用于查询混合报表",value = "reportForms/mixReportForms",method = {RequestMethod.POST})
    @AddLog(module = "财务模块",value = "该接口用于查询混合报表")
    public Map<String,Object> reportFormsList(@RequestBody String json){
        Map<String, Object> map =null;
        try {
            ListModel model = gson.fromJson(jsonParser.parse(json), ListModel.class);
            //校验传入的参数是否都符合要求
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(model, new HibernateValidator<ListModel>().Validator())
                    .doValidate()
                    .result(toComplex());
            //校验搜索时间
            try {
                if (!DataUtils.checkSearchDate(model.getStartTime(),model.getEndTime())){
                    return MsgTemplate.failureMsg(ReportFormsEnum.DATE_ERROR);
                }
            }catch (ParseException e){
                e.printStackTrace();
                return MsgTemplate.failureMsg(ReportFormsEnum.DATE_ERROR);
            }
            if(ret.isSuccess()){
                map = reportFormsService.mixReportFormslist(model);
            }else {
                return MsgTemplate.failureMsg(ret);
            }
        }catch (Exception e){
            logger.error("获取混合报表列表失败"+e.getCause());
            e.printStackTrace();
            return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
        }
        return map;
    }
    /**
     * 该接口用于查询混合报表之积分商城
     * @param json 分条件查询请求参数
     * @return 销售报表列表
     */
    @RequestMapping(name = "该接口用于查询混合报表之积分商城",value = "reportForms/mixReportOfMallForms",method = {RequestMethod.POST})
    public Map<String,Object> mixReportOfMallForms(@RequestBody String json){
        try {
            String jsonStr = reportFormsService.mixReportOfMallForms(json);
            return successMsg(jsonStr);
        }catch (Exception e){
            logger.error("查询混合报表之积分商城失败"+e.getCause());
            return failureMsg(SYS_ERROR);
        }
    }


    @RequestMapping(name = "该接口获取用于余额报表",value = "finance/balanceForms",method = {RequestMethod.POST})
    @AddLog(module = "财务模块",value = "该接口获取用于余额报表")
    public Map<String,Object> balanceFormsList(@RequestBody String json) {
        Map<String,Object> map = null;
        /* String  jsonStr;*/
            try {
            ListModel model = gson.fromJson(jsonParser.parse(json), ListModel.class);
            //校验传入的参数是否都符合要求
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(model, new HibernateValidator<ListModel>().Validator())
                    .doValidate()
                    .result(toComplex());
            if (ret.isSuccess()) {
              map = reportFormsService.getBalanceFormslist(model);
            } else {
                return MsgTemplate.failureMsg(ret);
            }

        } catch (Exception e) {
            logger.error("获取用于余额报表失败");
            e.printStackTrace();
            return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
        }
        return map;
    }

  @RequestMapping(name = "该接口通过手机号或认证名查询用户id",value = "outerUser/selectUserIdByPhoneOrCustomerName",method = {RequestMethod.POST})
  public Map<String,Object> selectUserIdByPhoneOrCustomerName(@RequestBody String json){
      try {
          String jsonStr = reportFormsService.selectUserIdByPhoneOrCustomerName(json);
          return successMsg(jsonStr);
      }catch (Exception e){
          logger.error("查询外部用户失败"+e.getCause());
          e.printStackTrace();
          return failureMsg(SYS_ERROR);
      }
  }
    @RequestMapping(name = "该接口用于获取余额预警信息列表",value = "finance/selectBalanceWarningForms",method = {RequestMethod.POST})
    public Map<String,Object> selectBalanceWarningList(@RequestBody String json){
        Map<String,Object> map =null;
        try {
            ListModel model = gson.fromJson(jsonParser.parse(json), ListModel.class);
            //校验传入的参数是否都符合要求
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(model, new HibernateValidator<ListModel>().Validator())
                    .doValidate()
                    .result(toComplex());
            //校验传入时间是否正确
            try {
                if (!DataUtils.checkSearchDate(model.getStartTime(),model.getEndTime())){
                    return MsgTemplate.failureMsg(ReportFormsEnum.DATE_ERROR);
                }
            }catch (ParseException e){
                e.printStackTrace();
                return MsgTemplate.failureMsg(ReportFormsEnum.DATE_ERROR);
            }
            if (ret.isSuccess()) {
                map = reportFormsService.selectBalanceWarningList(model);
            } else {
                return MsgTemplate.failureMsg(ret);
            }

        } catch (Exception e) {
            logger.error("获取用于余额预警报表失败");
            e.printStackTrace();
            return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
        }
        return map;
    }


   @RequestMapping(name = "该接口获取用于余额收款明细 ",value = "finance/balanceCollectionForms",method = {RequestMethod.POST})
   public Map<String,Object> balanceCollectionForms(@RequestBody String json) {
       Map<String,Object> map =null;
       try {
           ListModel model = gson.fromJson(jsonParser.parse(json), ListModel.class);
           //校验传入的参数是否都符合要求
           ComplexResult ret = FluentValidator.checkAll().failFast()
                   .on(model, new HibernateValidator<ListModel>().Validator())
                   .doValidate()
                   .result(toComplex());
           //校验传入时间是否正确
           try {
               if (!DataUtils.checkSearchDate(model.getStartTime(),model.getEndTime())){
                   return MsgTemplate.failureMsg(ReportFormsEnum.DATE_ERROR);
               }
           }catch (ParseException e){
               e.printStackTrace();
               return MsgTemplate.failureMsg(ReportFormsEnum.DATE_ERROR);
           }
           if (ret.isSuccess()) {
               map = reportFormsService.balanceCollectionForms(model);
           } else {
               return MsgTemplate.failureMsg(ret);
           }

       } catch (Exception e) {
           logger.error("该接口获取用于余额收款明细");
           e.printStackTrace();
           return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
       }
       return map;
   }
    /**
     /**
     * 该接口余额收款明细
     * @param json 分条件查询请求参数
     * @return 余额收款明细列表
     */
    @RequestMapping(name = "该接口用于余额收款明细列表",value = "finance/consumerForms",method = {RequestMethod.POST})
    public Map<String,Object> consumerForms(@RequestBody String json){
        Map<String, Object> map  = null;
        try {
            ListModel model = gson.fromJson(jsonParser.parse(json), ListModel.class);
            //校验传入的参数是否都符合要求
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(model, new HibernateValidator<ListModel>().Validator())
                    .doValidate()
                    .result(toComplex());
            //校验搜索时间
            try {
                if (!DataUtils.checkSearchDate(model.getStartTime(),model.getEndTime())){
                    return MsgTemplate.failureMsg(ReportFormsEnum.DATE_ERROR);
                }
            }catch (ParseException e){
                e.printStackTrace();
                return MsgTemplate.failureMsg(ReportFormsEnum.DATE_ERROR);
            }
            if(ret.isSuccess()){
                map = reportFormsService.consumerForms(model);
            }else {
                return MsgTemplate.failureMsg(ret);
            }
        }catch (Exception e){
            logger.error("获取消费报表失败"+e.getCause());
            e.printStackTrace();
            return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
        }
        return map;
    }


    /**
    /**
     * 该接口用于查询销售报表列表
     * @param json 分条件查询请求参数
     * @return 销售报表列表
     */
    @RequestMapping(name = "该接口用于查询销售报表列表",value = "reportForms/sellReportForms",method = {RequestMethod.POST})
    @AddLog(module = "财务模块",value = "该接口用于查询销售报表列表")
    public Map<String,Object> sellReportFormslist(@RequestBody String json){
        Map<String, Object> map ;
        try {
            ListModel model = gson.fromJson(jsonParser.parse(json), ListModel.class);
            //校验传入的参数是否都符合要求
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(model, new HibernateValidator<ListModel>().Validator())
                    .doValidate()
                    .result(toComplex());
            //校验搜索时间
            try {
                if (!DataUtils.checkSearchDate(model.getStartTime(),model.getEndTime())){
                    return MsgTemplate.failureMsg(ReportFormsEnum.DATE_ERROR);
                }
            }catch (ParseException e){
                e.printStackTrace();
                return MsgTemplate.failureMsg(ReportFormsEnum.DATE_ERROR);
            }
            if(ret.isSuccess()){
                map = reportFormsService.sellReportFormslist(model);
            }else {
                return MsgTemplate.failureMsg(ret);
            }
        }catch (Exception e){
            logger.error("获取混合报表列表失败"+e.getCause());
            e.printStackTrace();
            return MsgTemplate.failureMsg(ReportFormsEnum.FALSE);
        }
        return map;
    }

}
