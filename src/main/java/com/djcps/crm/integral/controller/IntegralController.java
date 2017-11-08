package com.djcps.crm.integral.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.crm.aop.inneruser.annotation.InnerUser;
import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.commons.utils.GsonUtils;
import com.djcps.crm.inneruser.model.InnerUserModel;
import com.djcps.crm.integral.enums.IntegralMsgEnum;
import com.djcps.crm.integral.model.ChangeUserIntegralRequest;
import com.djcps.crm.integral.model.JournalListRequest;
import com.djcps.crm.integral.service.IntegralService;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validation;
import java.math.BigInteger;
import java.util.Map;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;
import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;

/**
 * Created by TruthBean on 2017-08-09 13:22.
 */
@RestController
@RequestMapping("/integral/")
public class IntegralController {

    private static final Logger logger = LoggerFactory.getLogger(IntegralController.class);

    @Autowired
    private IntegralService integralService;

    /**
     * 获取所有(部分)用户积分余额
     * <p>
     * Created by TruthBean on 2017-08-09 13:23
     *
     * @return
     */
    @RequestMapping(value = "getAllUserIntegral", method = RequestMethod.POST)
    @AddLog(module = "积分服务",value = "获取所有(部分)用户积分余额")
    protected Map<String, Object> getAllUserIntegral(@RequestBody(required = false) String json) {

        logger.debug("params: {}", json);
        try {
            JsonObject jsonObject = GsonUtils.getJsonObject(json);
            String version = jsonObject.has("version") ? jsonObject.get("version").getAsString() : "1.0";
            int current = jsonObject.has("current") ? jsonObject.get("current").getAsInt() : 1;
            int size = jsonObject.has("size") ? jsonObject.get("size").getAsInt() : 20;
            String userNamekeyWord = jsonObject.has("username") ? jsonObject.get("username").getAsString() : "";
            String phoneKeyWord = jsonObject.has("phone") ? jsonObject.get("phone").getAsString() : "";
            JsonObject integrals = integralService.getSomeUserIntegralBySearch(userNamekeyWord, phoneKeyWord, current, size);
            if (integrals != null)
                return successMsg(integrals);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failureMsg(IntegralMsgEnum.OPS_FAILURE);
    }


    /**
     * 获取用户积分明细
     * <p>
     * Created by TruthBean on 2017-08-09 20:58
     *
     * @param json {"version":"1.0","current":"1","size":"20","fuserid":"39efe3e3-5a0e-11e7-a226-000c29af68e8"}
     *             或
     *             {"version":"1.0","ftype":10,"current":2,"size":10,"fkeyarea":"3302","fuserid":["39efe3e3-5a0e-11e7-a226-000c29af68e8","97e7cce1-729d-40c3-939a-de2989c63af6"],"fpartner":"337"}
     * @return
     */
    @RequestMapping(value = "journal/list", method = RequestMethod.POST)
    @AddLog(module = "积分服务",value = "获取用户积分明细")
    protected Map<String, Object> integralJournalList(@RequestBody(required = false) String json) {

        logger.debug("params: {}", json);

        if (json == null || "".equals(json)) {
            return failureMsg(IntegralMsgEnum.PARAMS_NULL);
        }

        try {
            JournalListRequest request = GsonUtils.fromJson(json, JournalListRequest.class);
            if (request == null) {
                return failureMsg(IntegralMsgEnum.PARAMS_ERROR);
            }
            if (request.getCurrent() == 0) {
                //默认第一页
                request.setCurrent(1);
            }
            if (request.getSize() == 0) {
                //默认20条
                request.setSize(20);
            }
            if (
                    (request.getSearchname() == null && request.getPhone() == null && request.getFuserid() == null)
                            || ("".equals(request.getSearchname()) && "".equals(request.getPhone()) && "".equals(request.getFuserid()))
                            || (
                            (
                                    (request.getSearchname() != null && !"".equals(request.getSearchname()))
                                            && (request.getPhone() != null || !"".equals(request.getPhone()))
                            ) && (
                                    request.getFuserid() != null && !"".equals(request.getFuserid())
                            )
                    )
                    ) {
                return failureMsg(IntegralMsgEnum.PARAMS_ERROR);
            }
            Map<String, Object> result = integralService.getUserintegralJournalList(request);
            if (result != null)
                return successMsg(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return failureMsg(IntegralMsgEnum.OPS_FAILURE);
    }

    /**
     * 积分调整
     * <p>
     * Created by TruthBean on 2017-08-10 13:16
     *
     * @param json [{"fuserid":"39efe3e3-5a0e-11e7-a226-000c29af68e8","fintegeral":66,"fintegeraljournaltype":9,
     *             "fkeyarea":"3302","fremark":"测试线下导入"}]
     * @return
     */
    @RequestMapping(value = "updateUserIntegral", method = RequestMethod.POST)
    @AddLog(module = "积分服务",value = "积分调整")
    protected Map<String, Object> updateUserIntegral(@InnerUser InnerUserModel innerUserModel,
                                                     @RequestBody(required = false) String json) {

        logger.debug("params : {}", json);

        try {
            if (json == null) {
                return failureMsg(IntegralMsgEnum.PARAMS_NULL);
            }

            ChangeUserIntegralRequest request = GsonUtils.fromJson(json, ChangeUserIntegralRequest.class);

            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(request, new HibernateSupportedValidator<ChangeUserIntegralRequest>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate()
                    .result(toComplex());
            if (ret.isSuccess() && request != null) {

                if (BigInteger.ZERO.compareTo(request.getFintegeral()) == 0) {
                    return failureMsg(IntegralMsgEnum.PARAMS_ERROR);
                }
                request.setFpartnerid(Integer.toString(innerUserModel.getId()));
                request.setFpartnerFkeyarea(Integer.parseInt(innerUserModel.getOcode()));
                request.setFcreater(Integer.toString(innerUserModel.getId()));
                request.setFintegeralruletype(3);

                boolean result = integralService.updateUserIntegral(request);
                if (result) {
                    return successMsg();
                }

            } else {
                logger.error(GsonUtils.toJson(ret.getErrors()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return failureMsg(IntegralMsgEnum.OPS_FAILURE);
    }
}
