package com.djcps.crm.partners.request;

import com.djcps.crm.commons.config.ParamsConfig;
import retrofit2.http.*;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

import java.util.Map;

/**
 * 调用oa数据
 * Created by L-wenbin on 2017/7/10.
 */
@RPCClientFields(urlfield ="OR_SYS",urlbean = ParamsConfig.class)
public interface PartnersInterface {

    /**
     * 合作商列表
     */
    @GET("partnerInfoList.org")
    HTTPResponse findAllPartnersByOA(@Query("keyWord") String keyWord,
                                     @Query("provinceKW") String provinceKW,
                                     @Query("cityKW") String cityKW,
                                     @Query("page") String page,
                                     @Query("pageSize") String pageSize);

    /**
     * 获取oa所有合作商信息
     * @return
     */
    @GET("partnerInfos.org")
    HTTPResponse findOaPartners();

    /**
     * 根据oaid，获取组织中合作商的详细
     * @param id
     * @return
     */
    @GET("partnerInfoDetail.org")
    HTTPResponse findPartnerByOaid(@Query("id")int id);

    /**
     * 修改合作方登陆crm的权限状态
     * @param companyID 合作方 oaid
     * @param crm   修改状态  1-启用  0-禁用
     * @return
     */
    @FormUrlEncoded
    @POST("orgBussionChangeFromCompany.org")
    HTTPResponse upPartnerStatus(@Field("companyID") int companyID,
                                 @Field("crm") int crm);
}
