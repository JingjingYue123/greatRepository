package com.djcps.crm.outeruser.controller;


import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.outeruser.service.OuterUserService;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.djcps.crm.commons.msg.MsgTemplate.failureMsg;
import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;
import static com.djcps.crm.outeruser.enums.OuterUserMsgEnum.PARAMS_ERROR;
import static com.djcps.crm.outeruser.enums.OuterUserMsgEnum.SYS_ERROR;
import static com.djcps.crm.outeruser.enums.OuterUserMsgEnum.TIMEOUT_ERROR;

/**
 * Created by Lancher on 2017/7/3.
 */

@RestController
public class OuterUserController {

    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();
    @Autowired
    private OuterUserService outerUserService;

    /**
     * 用于重置支付/登录密码：
     * 接口逻辑为点击修改登录密码或者支付密码，就直接将客户的密码修改为一个随机数，
     * 再短信通知客户新密码，主要针对于那些不会自己修改密码的low B !
     * <p>
     * //@param userId      用户id
     * //@param payPassword 支付密码
     * //@param password    登录密码
     * //@param version     版本号
     *
     * @return nicai
     */
    @RequestMapping(name = "用于重置支付/登录密码", value = "/outerUser/remakePasswd", method = {RequestMethod.POST})
    @AddLog(module = "外部用户模块",value = "用于重置支付/登录密码")
    public Map<String, Object> remakePasswd(@RequestBody(required = false) String json) {
        try {
            String jsonStr = outerUserService.changeOuterUserPassword(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(PARAMS_ERROR);
        }
    }

    /**
     * 内部用户启用 被禁用的主账号
     *
     * @param json 包含（version 版本号 userId 用户id）
     * @return gg
     */
    @RequestMapping(name = "外部用户启用 被禁用的主账号", value = "/outerUser/enableAccount", method = {RequestMethod.POST})
    @AddLog(module = "外部用户模块",value = "外部用户启用 被禁用的主账号")
    public Map<String, Object> enableAccount(@RequestBody(required = false) String json) {
        try {
            String jsonStr = outerUserService.enableAccount(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(PARAMS_ERROR);
        }

    }

    /**
     * 外部用户禁用 已启用的主账号
     *
     * @param json 包含（version 版本号 userId 用户id）
     * @return gg
     */
    @RequestMapping(name = "外部用户禁用 已启用的主账号", value = "/outerUser/disableAccount", method = {RequestMethod.POST})
    @AddLog(module = "外部用户模块",value = "外部用户禁用 已启用的主账号")
    public Map<String, Object> disableAccount(@RequestBody(required = false) String json) {
        try {
            String jsonStr  = outerUserService.disableAccount(json);
             return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(PARAMS_ERROR);
        }
    }

    /**
     * 外部用户获取用户详细信息
     *
     * @param json 包含（version 版本号 userId 用户id）
     * @return gg
     */
    @RequestMapping(name = "外部用户获取用户详细信息", value = "/outerUser/getOuterDetailInfo", method = {RequestMethod.POST})
    @AddLog(module = "外部用户模块",value = "外部用户获取用户详细信息")
    public Map<String, Object> getOuterDetailInfo(@RequestBody(required = false) String json) {
        try {
            String jsonStr = outerUserService.getUserInfo(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(PARAMS_ERROR);
        }
    }


    /**
     * 外部用户获取用户列表
     *
     * @param json 包含（version 版本号 page 页码  pageSize 每页显示数  userJson 查询条件Json ）
     * @return gg
     */
    @RequestMapping(name = "该接口用于查询外部用户列表", value = "/outerUser/loadLst", method = {RequestMethod.POST})
    @AddLog(module = "外部用户模块",value = "该接口用于查询外部用户列表")
    public Map<String, Object> loadLst(@RequestBody(required = false) String json) {
        try {
            String jsonStr = outerUserService.getMainUserList(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(PARAMS_ERROR);
        }
    }

    /**
     * 该接口用于查询认证审核列表
     * @param json  包含（version 版本号 page 页码  pageSize 每页显示数  json 查询条件: 手机号 用户名称 认证状态 ）
     * @return gg
     */
    @RequestMapping(name = "该接口用于查询认证审核列表", value = "/outerUser/loadAuditList", method = {RequestMethod.POST})
    @AddLog(module = "外部用户模块",value = "该接口用于查询认证审核列表")
    public Map<String, Object> loadAuditList(@RequestBody(required = false) String json){
        try {
            String jsonStr = outerUserService.getAuditList(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 该接口用于用户的认证审核
     * @param json  包含（version 版本号 json:
     *              operationType 操作类型 1通过 2驳回；certificationType 认证类型 1个人 2企业 companyCertification 公司信息json  personCertification个人信息json）
     * @return gg
     */
    @RequestMapping(name = "该接口用于用户的认证审核", value = "/outerUser/userAudit", method = {RequestMethod.POST})
    @AddLog(module = "外部用户模块",value = "该接口用于用户的认证审核")
    public Map<String, Object> userAudit(@RequestBody(required = false) String json){
        try {
            String jsonStr = outerUserService.userAudit(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }


    /**
     * 该接口用于查询认证审核详情
     * @param json  包含（version 版本号 json: --- ）
     * @return gg
     */
    @RequestMapping(name = "该接口用于查询认证审核详情", value = "/outerUser/loadAuditDetail", method = {RequestMethod.POST})
    @AddLog(module = "外部用户模块",value = "该接口用于查询认证审核详情")
    public Map<String, Object> detailInfo(@RequestBody(required = false) String json){
        try {
            String jsonStr = outerUserService.getAuditDetail(json);
            return successMsg(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }

    /**
     * 认证图片临时URL获取接口
     * @param jsonParm
     * @return
     */
    @RequestMapping(name = "该接口用于获取认证图片临时URL", value = "/outerUser/getPicture", method = {RequestMethod.POST})
    @AddLog(module = "外部用户模块",value = "该接口用于获取认证图片临时URL")
    public Map<String, Object> getPicture(@RequestBody(required = false) String jsonParm){
        try {
            String jsonData = outerUserService.getPicture(jsonParm);
            return successMsg(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return failureMsg(SYS_ERROR);
        }
    }
}
