package com.djcps.crm.integral.service;

import com.djcps.crm.commons.utils.GsonUtils;
import com.djcps.crm.commons.utils.StringUtils;
import com.djcps.crm.inneruser.model.InnerUserModel;
import com.djcps.crm.integral.model.ChangeUserIntegralRequest;
import com.djcps.crm.integral.model.JournalListRequest;
import com.djcps.crm.integral.model.UserIntegral;
import com.djcps.crm.integral.model.UserIntegralJournal;
import com.djcps.crm.integral.server.IntegralServer;
import com.djcps.crm.integral.server.OutUserServer;
import com.djcps.crm.partners.dao.PartnersDao;
import com.djcps.crm.partners.model.Partners;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import rpc.plugin.http.HTTPResponse;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TruthBean on 2017-08-09 13:23.
 */
@Service
public class IntegralService {

    @Autowired
    private IntegralServer integralServer;

    @Autowired
    private OutUserServer outUserServer;

    @Autowired
    private PartnersDao partnersDao;

    private static final Logger logger = LoggerFactory.getLogger(IntegralService.class);

    //getUserInfo
    public JsonObject getSomeUserIntegralBySearch(String userNamekeyWord, String phoneKeyWord, int current, int size) {
        String json = "{\"version\":\"1.0\",\"username\":\"" + userNamekeyWord + "\",\"current\":\"" + current +
                "\",\"size\":\"" + size + "\",\"phone\":\"" + phoneKeyWord + "\"}";
        logger.debug("getSomeUserIntegralBySearch params is {}", json);
        HTTPResponse response = integralServer.getUserIntegralBySearch(json);
        if (response != null) {
            if (response.isSuccessful()) {
                String body = response.getBodyString();
                logger.debug("getSomeUserIntegralBySearch result is {}", body);
                JsonObject jsonObject = GsonUtils.getJsonObject(body);
                if (jsonObject.has("success") && jsonObject.get("success").getAsBoolean()) {
                    if (jsonObject.has("data")) {
                        return jsonObject.get("data").getAsJsonObject();
                    }
                }
            }
        }
        return null;
    }

    //getUserInfo
    public Map<String, Object> getSomeUserIntegral(String userNamekeyWord, String phoneKeyWord, int current, int size) {
        Map<String, Object> map = new HashMap<>();
        map.put("current", current);
        map.put("size", size);

        boolean isReturnNull = true;
        //获取搜索的用户信息
        Map<String, Object> userIntegralList;
        userIntegralList = getUserInfoBySearch(userNamekeyWord, phoneKeyWord, current, size);

        //用户ID和用户区域
        List<Map<String, String>> userIds = null;
        if (userIntegralList != null) {
            userIds = (List<Map<String, String>>) userIntegralList.get("userIds");
            if (userIds != null && !userIds.isEmpty()) {
                map.put("params", userIds);
                isReturnNull = false;
            }
        }

        Map<String, Object> result = new HashMap<>();
        //如果搜索结果为空，直接返回空
        if (!isReturnNull) {
            String json = GsonUtils.toJson(map);
            //用户积分列表
            Map<String, Object> mapList = getAllUserIntegralList(json);
            if (mapList != null) {
                List<UserIntegral> list = (List<UserIntegral>) mapList.get("list");
                result.put("total", mapList.get("total"));
                if (list != null) {
                    if ("".equals(userNamekeyWord)) {
                        List<String> ids = new ArrayList<>();
                        for (UserIntegral userIntegral : list) {
                            ids.add(userIntegral.getFuserid());
                        }
                        if (setOutUserInfoList(list, ids)) {
                            result.put("total", 0);
                            result.put("list", new ArrayList<>());
                            return result;
                        }

                    } else {
                        List<UserIntegral> userIntegrals = (List<UserIntegral>) userIntegralList.get("userIntegral");
                        for (UserIntegral userIntegral : list) {
                            for (UserIntegral ui : userIntegrals) {
                                if (userIntegral.getFuserid().equals(ui.getFuserid())) {
                                    userIntegral.setFcustName(ui.getFcustName());
                                    userIntegral.setFphone(ui.getFphone());
                                }
                            }
                        }
                    }
                }
                result.put("list", list);
                return result;
            }
        }

        result.put("total", 0);
        result.put("list", new ArrayList<>());
        return result;
    }

    @Deprecated
    public Map<String, Object> getAllUserIntegral(String userNamekeyWord, String phoneKeyWord, int current, int size) {
        Map<String, Object> map = new HashMap<>();
        map.put("current", current);
        map.put("size", size);

        boolean isReturnNull = true;
        //获取搜索的用户信息
        Map<String, Object> userIntegralList;

        //用户ID和用户区域
        List<Map<String, String>> userIds = null;
        if (!"".equals(userNamekeyWord) && userNamekeyWord != null) {
            userIntegralList = getUserInfoBySearchName(userNamekeyWord, current, size);
            //userIntegralList = getUserInfoBySearch(userNamekeyWord);
            if (userIntegralList != null) {
                userIds = (List<Map<String, String>>) userIntegralList.get("userIds");
                if (userIds != null && !userIds.isEmpty()) {
                    map.put("params", userIds);
                    isReturnNull = false;
                }
            }
        }

        if ("".equals(phoneKeyWord) || phoneKeyWord == null) {
            //获取所有用户
            phoneKeyWord = "1";
        }
        userIntegralList = getUserInfoBySearchPhone(phoneKeyWord, current, size);
        if (userIntegralList != null) {
            List<Map<String, String>> userIds1 = (List<Map<String, String>>) userIntegralList.get("userIds");
            if (userIds1 != null && !userIds1.isEmpty()) {
                List<Map<String, String>> resultUserIds = new ArrayList<>();
                if (userIds != null && !userIds.isEmpty()) {
                    for (Map<String, String> userIdsMap : userIds)
                        for (Map<String, String> stringMap : userIds1) {
                            if (stringMap.get("fuserid").equals(userIdsMap.get("fuserid"))) {
                                resultUserIds.add(userIdsMap);
                            }
                        }
                } else {
                    resultUserIds.addAll(userIds1);
                }
                map.put("params", resultUserIds);
                isReturnNull = false;
            }
        }

        Map<String, Object> result = new HashMap<>();
        //如果搜索结果为空，直接返回空
        if (!isReturnNull) {
            String json = GsonUtils.toJson(map);
            //用户积分列表
            Map<String, Object> mapList = getAllUserIntegralList(json);
            if (mapList != null) {
                List<UserIntegral> list = (List<UserIntegral>) mapList.get("list");
                result.put("total", mapList.get("total"));
                if (list != null) {
                    if ("".equals(userNamekeyWord)) {
                        List<String> ids = new ArrayList<>();
                        for (UserIntegral userIntegral : list) {
                            ids.add(userIntegral.getFuserid());
                        }
                        if (setOutUserInfoList(list, ids)) {
                            result.put("total", 0);
                            result.put("list", new ArrayList<>());
                            return result;
                        }

                    } else {
                        List<UserIntegral> userIntegrals = (List<UserIntegral>) userIntegralList.get("userIntegral");
                        for (UserIntegral userIntegral : list) {
                            for (UserIntegral ui : userIntegrals) {
                                if (userIntegral.getFuserid().equals(ui.getFuserid())) {
                                    userIntegral.setFcustName(ui.getFcustName());
                                    userIntegral.setFphone(ui.getFphone());
                                }
                            }
                        }
                    }

                }
                result.put("list", list);
            }
            return result;
        }

        result.put("total", 0);
        result.put("list", new ArrayList<>());
        return result;

    }

    private boolean setOutUserInfoList(List<UserIntegral> list, List<String> ids) {
        //用户列表
        JsonElement jsonElement = getOutUserInfoList(ids);
        if (jsonElement != null && jsonElement.isJsonArray()) {
            JsonArray userInfoList = jsonElement.getAsJsonArray();
            if (userInfoList != null) {
                int length = userInfoList.size();
                JsonObject jsonObject;
                for (UserIntegral userIntegral : list) {
                    for (int a = 0; a < length; a++) {
                        jsonObject = userInfoList.get(a).getAsJsonObject();
                        //设置用户信息
                        setUserIntegral(jsonObject, userIntegral);
                    }
                }
            } else {
                return true;
            }
        } else if (jsonElement != null && jsonElement.isJsonObject()) {
            //如果返回 单条用户信息
            JsonObject jsonObject;
            for (UserIntegral userIntegral : list) {
                jsonObject = jsonElement.getAsJsonObject();
                setUserIntegral(jsonObject, userIntegral);
            }
        } else {
            return true;
        }
        return false;
    }

    private Map<String, Object> getUserInfoBySearchName(String userNamekeyWord) {
        String json = "{\"version\":\"1.0\",\"certificateName\":\"" + userNamekeyWord + "\"}";
        logger.debug("getUserInfoBySearch params is {}", json);
        HTTPResponse response = outUserServer.getUserInfoBySearchName(json);
        if (response != null) {
            if (response.isSuccessful()) {
                String body = response.getBodyString();
                logger.debug("getUserInfoBySearch result is {}", body);
                JsonObject jsonObject = GsonUtils.getJsonObject(body);
                if (jsonObject.has("success") && jsonObject.get("success").getAsBoolean()) {
                    if (jsonObject.has("data")) {
                        JsonArray userInfoList = jsonObject.get("data").getAsJsonArray();
                        if (userInfoList != null) {
                            int length = userInfoList.size();
                            UserIntegral userIntegral;
                            List<UserIntegral> list = new ArrayList<>();
                            Map<String, String> params;
                            List<Map<String, String>> userIds = new ArrayList<>();
                            Map<String, Object> result = new HashMap<>();
                            for (int a = 0; a < length; a++) {
                                params = new HashMap<>();
                                userIntegral = new UserIntegral();
                                jsonObject = userInfoList.get(a).getAsJsonObject();
                                //设置用户信息
                                setUserIntegral(jsonObject, userIntegral);
                                list.add(userIntegral);
                                params.put("fuserid", userIntegral.getFuserid());
                                params.put("fkeyarea", userIntegral.getFkeyArea());
                                userIds.add(params);
                            }
                            result.put("userIntegral", list);
                            result.put("userIds", userIds);
                            return result;

                        }
                    }
                }
            }
        }
        return null;
    }

    private Map<String, Object> getUserInfoBySearch(String userNamekeyWord, String phoneKeyWord, int current, int size) {
        String json = "{\"version\":\"1.0\",\"certificateName\":\"" + userNamekeyWord + "\",\"pageNo\":\"" + (current - 1) +
                "\",\"pageSize\":\"" + size + "\",\"phone\":\"" + phoneKeyWord + "\"}";
        logger.debug("getUserInfoBySearch params is {}", json);
        HTTPResponse response = outUserServer.getUserInfoBySearch(json);
        if (response != null) {
            if (response.isSuccessful()) {
                String body = response.getBodyString();
                logger.debug("getUserInfoBySearch result is {}", body);
                return handleUserInfo(body);
            }
        }
        return null;
    }

    private Map<String, Object> getUserInfoBySearchName(String userNamekeyWord, int current, int size) {
        String json = "{\"version\":\"1.0\",\"certificateName\":\"" + userNamekeyWord + "\",\"pageNo\":\"" + (current - 1) +
                "\",\"pageSize\":\"" + size + "\"}";
        logger.debug("getUserInfoBySearchName params is {}", json);
        HTTPResponse response = outUserServer.getUserInfoBySearchName(json);
        if (response != null) {
            if (response.isSuccessful()) {
                String body = response.getBodyString();
                logger.debug("getUserInfoBySearchName result is {}", body);
                return handleUserInfo(body);
            }
        }
        return null;
    }

    private Map<String, Object> handleUserInfo(String body) {
        JsonObject jsonObject = GsonUtils.getJsonObject(body);
        if (jsonObject.has("success") && jsonObject.get("success").getAsBoolean()) {
            if (jsonObject.has("data")) {

                JsonObject jsonObjectData = jsonObject.get("data").getAsJsonObject();

                JsonArray userInfoList = jsonObjectData.has("result") ? jsonObjectData.get("result").getAsJsonArray() : null;
                if (userInfoList != null) {
                    int length = userInfoList.size();
                    UserIntegral userIntegral;
                    List<UserIntegral> list = new ArrayList<>();
                    Map<String, String> params;
                    List<Map<String, String>> userIds = new ArrayList<>();
                    Map<String, Object> result = new HashMap<>();
                    for (int a = 0; a < length; a++) {
                        params = new HashMap<>();
                        userIntegral = new UserIntegral();
                        jsonObject = userInfoList.get(a).getAsJsonObject();
                        //设置用户信息
                        setUserIntegral(jsonObject, userIntegral);
                        list.add(userIntegral);
                        params.put("fuserid", userIntegral.getFuserid());
                        params.put("fkeyarea", userIntegral.getFkeyArea());
                        userIds.add(params);
                    }
                    result.put("userIntegral", list);
                    result.put("userIds", userIds);
                    if (jsonObjectData.has("total")) {
                        result.put("total", jsonObjectData.get("total").getAsInt());
                    }
                    return result;

                }
            }
        }
        return null;
    }

    private Map<String, Object> getUserInfoBySearchPhone(String userPhonekeyWord, int current, int size) {
        String json = "{\"version\":\"1.0\",\"phone\":\"" + userPhonekeyWord + "\",\"pageNo\":\"" + (current - 1) +
                "\",\"pageSize\":\"" + size + "\"}";
        logger.debug("getUserInfoBySearchPhone params is {}", json);
        HTTPResponse response = outUserServer.getUserInfoBySearchPhone(json);
        if (response != null) {
            if (response.isSuccessful()) {
                String body = response.getBodyString();
                logger.debug("getUserInfoBySearchPhone result is {}", body);
                return handleUserInfo(body);
            }
        }
        return null;
    }

    /**
     * 设置 用户积分余额信息
     *
     * @param jsonObject
     * @param userIntegral
     */
    private void setUserIntegral(JsonObject jsonObject, UserIntegral userIntegral) {
        if (jsonObject.has("fuserId")) {
            String userId = jsonObject.get("fuserId").getAsString();
            if ((userId != null && userId.equals(userIntegral.getFuserid())) || userIntegral.getFuserid() == null) {
                userIntegral.setFuserid(userId);
                String userName = null;
                JsonElement jsonElement;
                if (jsonObject.has("fcertificateName")) {
                    jsonElement = jsonObject.get("fcertificateName");
                    if (jsonElement != null && !jsonElement.isJsonNull())
                        userName = jsonObject.get("fcertificateName").getAsString();
                }
                if (userName == null || "".equals(userName)) {
                    if (jsonObject.has("fcustName")) {
                        jsonElement = jsonObject.get("fcustName");
                        if (jsonElement != null && !jsonElement.isJsonNull())
                            userName = jsonObject.get("fcustName").getAsString();
                    }
                }
                if (userName == null || "".equals(userName)) {
                    if (jsonObject.has("faccount")) {
                        jsonElement = jsonObject.get("faccount");
                        if (jsonElement != null && !jsonElement.isJsonNull())
                            userName = jsonObject.get("faccount").getAsString();
                    }
                }
                if (userName == null || "".equals(userName)) {
                    userIntegral.setFcustName("-");
                } else {
                    userIntegral.setFcustName(userName);
                }

                String fphone = jsonObject.has("fphone") ? jsonObject.get("fphone").getAsString() : "-";
                userIntegral.setFphone(fphone);
                String fkeyarea = null;
                if (jsonObject.has("fkeyArea")) {
                    fkeyarea = jsonObject.get("fkeyArea").getAsString();
                }
                if (jsonObject.has("fkeyarea")) {
                    fkeyarea = jsonObject.get("fkeyarea").getAsString();
                }
                userIntegral.setFkeyArea(fkeyarea);
                long updateTime = userIntegral.getFupdatetime();
                if (updateTime > 0)
                    userIntegral.setFupdatetimeStr(StringUtils.formatDateStr(updateTime));
            }
        }
    }

    /**
     * 可通过用户ID获取用户信息列表
     *
     * @param ids
     * @return
     */
    private JsonElement getOutUserInfoList(List<String> ids) {
        Map<String, Object> params = new HashMap<>();
        params.put("userIds", ids);
        params.put("version", "1.0");
        String idsJson = GsonUtils.toJson(params);
        logger.debug("getOutUserInfoListByUserIds params is {}", idsJson);
        HTTPResponse httpResponse = outUserServer.getOutUserInfoListByUserIds(idsJson);

        if (httpResponse != null) {
            if (httpResponse.isSuccessful()) {
                String body = httpResponse.getBodyString();
                logger.debug("getOutUserInfoListByUserIds result is {}", body);
                JsonObject jsonObject = GsonUtils.getJsonObject(body);
                if (jsonObject.has("success") && jsonObject.get("success").getAsBoolean()) {
                    if (jsonObject.has("data")) {
                        return jsonObject.get("data");
                    }
                } else {
                    logger.error("getOutUserInfoListByUserIds errors : {}", jsonObject.toString());
                }
            }
        }
        return null;
    }

    /**
     * 获取用户积分列表
     *
     * @param json
     * @return
     */
    private Map<String, Object> getAllUserIntegralList(String json) {
        HTTPResponse httpResponse = integralServer.getUserIntegralList(json);
        if (httpResponse != null) {
            if (httpResponse.isSuccessful()) {
                String body = httpResponse.getBodyString();
                JsonObject jsonObject = GsonUtils.getJsonObject(body);
                if (jsonObject.has("success") && jsonObject.get("success").getAsBoolean()) {
                    if (jsonObject.has("data")) {
                        JsonElement jsonElement = jsonObject.get("data");
                        logger.debug("getUserIntegralList data is {}", jsonElement.toString());

                        jsonObject = jsonElement.getAsJsonObject();
                        int total = jsonObject.get("total").getAsInt();
                        jsonElement = jsonObject.get("list");
                        if (jsonElement != null && jsonElement.isJsonArray()) {
                            JsonArray jsonArray = jsonElement.getAsJsonArray();
                            Map<String, Object> result = new HashMap<>();
                            List<UserIntegral> list = GsonUtils.toListBean(jsonArray, UserIntegral[].class);
                            for (UserIntegral userIntegral : list) {
                                if (userIntegral.getFupdatetime() > 0)
                                    userIntegral.setFupdatetimeStr(StringUtils.formatDateStr(userIntegral.getFupdatetime()));
                            }
                            result.put("total", total);
                            result.put("list", list);
                            return result;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取用户积分明细
     *
     * @param request
     * @return
     */
    public Map<String, Object> getUserintegralJournalList(JournalListRequest request) {
        Map<String, Object> map = new HashMap<>();

        map.put("current", request.getCurrent());
        map.put("size", request.getSize());
        if (request.getBegintime() != null && !"".equals(request.getBegintime().trim())) {
            map.put("orderStartTime", request.getBegintime());
        }
        if (request.getEndtime() != null && !"".equals(request.getEndtime())) {
            map.put("orderEndTime", request.getEndtime());
        }
        if (request.getFtype() > 0) {
            map.put("ftype", request.getFtype());
        }
        if (request.getFpartner() != null && !"".equals(request.getFpartner())) {
            map.put("fpartner", request.getFpartner());
        }
        if (request.getFkeyarea() > 0) {
            map.put("fkeyarea", request.getFkeyarea());
        }
        if (request.getSearchname() != null && !"".equals(request.getSearchname())) {
            map.put("searchName", request.getSearchname());
        }
        if (request.getPhone() != null && !"".equals(request.getPhone())) {
            map.put("searchPhone", request.getPhone());
        }

        if (request.getFuserid() != null && !"".equals(request.getFuserid())) {
            map.put("fuserid", request.getFuserid());
            String json = GsonUtils.toJson(map);
            logger.debug("getUserIntegralJournalList params is {}", json);
            HTTPResponse httpResponse = integralServer.getUserIntegralJournalList(json);
            return getUserIntegralJournalList(httpResponse);
        } else {
            String json = GsonUtils.toJson(map);
            logger.debug("getMultiUserIntegralJournalList params is {}", json);
            HTTPResponse httpResponse = integralServer.getMultiUserIntegralJournalList(json);
            return getUserIntegralJournalList(httpResponse);
        }

    }

    private Map<String, Object> getUserIntegralJournalList(HTTPResponse httpResponse) {
        if (httpResponse != null && httpResponse.isSuccessful()) {
            String body = httpResponse.getBodyString();
            logger.debug("getUserIntegralJournalList result: {}", body);
            JsonObject jsonObject = GsonUtils.getJsonObject(body);
            if (jsonObject.has("success") && jsonObject.get("success").getAsBoolean()) {
                if (jsonObject.has("data")) {
                    JsonElement jsonElement = jsonObject.get("data");
                    if (jsonElement != null && jsonElement.isJsonObject()) {
                        JsonObject object = jsonElement.getAsJsonObject();
                        int total = 0;
                        if (object.has("total")) {
                            total = object.get("total").getAsInt();
                        }
                        if (object.has("list")) {
                            JsonArray jsonArray = object.get("list").getAsJsonArray();
                            logger.debug("getUserintegralJournalList data is {}", jsonArray.toString());
                            List<UserIntegralJournal> list = GsonUtils.toListBean(jsonArray, UserIntegralJournal[].class);

                            List<Integer> oaids = new ArrayList<>();
                            List<String> userids = new ArrayList<>();
                            for (UserIntegralJournal userIntegralJournal : list) {
                                oaids.add(userIntegralJournal.getFpartner());
                                userids.add(userIntegralJournal.getFuserid());
                            }
                            setPartnerName(list, oaids);
                            setUserInfo(list, userids);
                            setCreaterInfo(list);
                            setIntegralJournalType(list);

                            Map<String, Object> result = new HashMap<>();
                            result.put("total", total);
                            result.put("list", list);

                            return result;
                        }
                    }
                }
            }
        }
        return null;
    }

    private void setIntegralJournalType(List<UserIntegralJournal> list) {
        for (UserIntegralJournal userIntegralJournal : list) {
            switch (userIntegralJournal.getFtype()) {
                case 0:
                    userIntegralJournal.setFtypename("全部");
                    break;
                case 1:
                    userIntegralJournal.setFtypename("购买产品送积分");
                    break;
                case 2:
                    userIntegralJournal.setFtypename("商城订单取消");
                    break;
                case 3:
                    userIntegralJournal.setFtypename("商城消费");
                    break;
                case 4:
                    userIntegralJournal.setFtypename("团购订单取消");
                    break;
                case 5:
                    userIntegralJournal.setFtypename("社区活动积分");
                    break;
                case 6:
                    userIntegralJournal.setFtypename("商城活动积分");
                    break;
                case 7:
                    userIntegralJournal.setFtypename("包辅活动积分");
                    break;
                case 8:
                    userIntegralJournal.setFtypename("会员返利积分");
                    break;
                case 9:
                    userIntegralJournal.setFtypename("专享返利积分");
                    break;
                case 10:
                    userIntegralJournal.setFtypename("其他返利积分");
                    break;
                case 11:
                    userIntegralJournal.setFtypename("充值送积分");
                    break;
                case 12:
                    userIntegralJournal.setFtypename("充值失败扣积分");
                    break;
                case 13:
                    userIntegralJournal.setFtypename("商城订单取消异常");
                    break;
                case 14:
                    userIntegralJournal.setFtypename("商城下单异常");
                    break;
                case 15:
                    userIntegralJournal.setFtypename("团购下单异常");
                    break;
                case 16:
                    userIntegralJournal.setFtypename("团购订单取消异常");
                    break;
            }
        }
    }

    @Resource(name = "userJedisPool")
    private JedisPool userJedisPool;

    private void setCreaterInfo(List<UserIntegralJournal> list) {
        InnerUserModel creator;
        String userinfo;
        for (UserIntegralJournal userIntegralJournal : list) {
            try (Jedis redis = userJedisPool.getResource()) {
                userinfo = redis.get("userInfo" + userIntegralJournal.getFcreator());
                if (userinfo != null) {
                    creator = GsonUtils.fromJson(userinfo, InnerUserModel.class);
                    userIntegralJournal.setCreaterName(creator.getUname());
                } else {
                    userIntegralJournal.setCreaterName("-");
                }
                userIntegralJournal.setFcreator(userIntegralJournal.getFcreator());

            }
        }
    }

    /**
     * 设置合作方名称、区域、手机号码
     *
     * @param userIntegralJournals
     * @param oaids
     */
    private void setPartnerName(List<UserIntegralJournal> userIntegralJournals, List<Integer> oaids) {
        if (oaids != null && oaids.size() > 0) {
            List<Partners> list = partnersDao.findPartnersByIds(oaids);
            try (Jedis jedis = userJedisPool.getResource()) {
                for (UserIntegralJournal userIntegralJournal : userIntegralJournals) {
                    for (Partners partners : list) {
                        if (partners.getId() == userIntegralJournal.getFpartner()) {
                            userIntegralJournal.setPartnerName(partners.getOname());
                            userIntegralJournal.setPartnerArea(partners.getOcode());
                            userIntegralJournal.setPartnerAreaName(partners.getOprovince() + "-" + partners.getOcity());
                            String creator = jedis.get("userInfo" + userIntegralJournal.getFcreator());
                            if (creator == null) {
                                creator = "-";
                            } else {
                                InnerUserModel innerUserModel = GsonUtils.fromJson(creator, InnerUserModel.class);
                                creator = innerUserModel.getUname();
                            }
                            userIntegralJournal.setCreaterName(creator);
                        }
                    }
                }
            }

        }
    }

    /**
     * 设置客户名称、手机号码
     *
     * @param list
     */
    private void setUserInfo(List<UserIntegralJournal> list, List<String> userids) {
        JsonElement jsonElement = getOutUserInfoList(userids);
        if (jsonElement != null && jsonElement.isJsonArray()) {
            JsonArray userInfoList = jsonElement.getAsJsonArray();
            if (userInfoList != null) {
                int length = userInfoList.size();
                JsonObject jsonObject;
                for (UserIntegralJournal userIntegralJournal : list) {
                    for (int a = 0; a < length; a++) {
                        jsonObject = userInfoList.get(a).getAsJsonObject();
                        setUserIntegral(jsonObject, userIntegralJournal);
                    }
                }
            }
        } else if (jsonElement != null && jsonElement.isJsonObject()) {
            JsonObject jsonObject;
            for (UserIntegralJournal userIntegralJournal : list) {
                jsonObject = jsonElement.getAsJsonObject();
                setUserIntegral(jsonObject, userIntegralJournal);
            }
        }
    }

    /**
     * 设置用户信息
     *
     * @param jsonObject
     * @param userIntegral
     */
    private void setUserIntegral(JsonObject jsonObject, UserIntegralJournal userIntegral) {
        if (jsonObject.has("fuserId")) {
            String userId = jsonObject.get("fuserId").getAsString();
            if (userIntegral.getFuserid().equals(userId)) {
                String fcustName = "-";
                if (jsonObject.has("fcustName")) {
                    JsonElement object = jsonObject.get("fcustName");
                    if (object.isJsonObject() || object.isJsonPrimitive()) {
                        fcustName = jsonObject.get("fcustName").getAsString();
                        if ("".equals(fcustName)) {
                            fcustName = "-";
                        }
                    }
                }
                userIntegral.setFcustName(fcustName);

                String fphone = jsonObject.has("fphone")
                        ? jsonObject.get("fphone").getAsString() : "-";
                userIntegral.setFphone(fphone);
            }
        }
    }

    public boolean updateUserIntegral(ChangeUserIntegralRequest request) {
        //客户ID
        //积分
        //积分类型
        //备注
        //操作人
        //[{"fuserid":"39efe3e3-5a0e-11e7-a226-000c29af68e8","fpartnerid":"oaid",
        // "fintegeral":66,"fintegeraljournaltype":9,"fintegeralruletype":"3","fkeyarea":"3302","fpartnerFkeyarea":3303,
        // "fcreater":"1000000","fremark":"测试线下导入"}]
        /**
         * 积分流水类型：
         *
         * 线下类型 3
         * 5	社区活动积分	加积分
         * 6	商城活动积分	加积分
         * 7	包辅活动积分	加积分
         * 8	会员返利积分	加积分
         * 9	专享返利积分	加积分
         * 10	其他返利积分	加积分
         */
        List<ChangeUserIntegralRequest> list = new ArrayList<>();
        list.add(request);
        String json = GsonUtils.toJson(list);
        HTTPResponse httpResponse = integralServer.updateUserIntegral(json);
        if (httpResponse != null) {
            if (httpResponse.isSuccessful()) {
                String body = httpResponse.getBodyString();
                JsonObject jsonObject = GsonUtils.getJsonObject(body);
                return (jsonObject.has("success") && jsonObject.get("success").getAsBoolean());
            }
        }
        return false;
    }
}