package com.djcps.crm.address.controller;

import com.djcps.crm.address.enums.AddressMsgEnum;
import com.djcps.crm.address.service.AddressService;
import com.djcps.crm.commons.msg.MsgTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.djcps.crm.commons.msg.MsgTemplate.successMsg;

/**
 * Created by ysy on 2017/7/5 20:31.
 */
@Controller
@ResponseBody
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 查询省份
     * @param parm   code 默认为0 查询所有的省份
     * @return
     */
    @RequestMapping(name = "查询省份",value = "/address/findProvince",method = {RequestMethod.POST})
    public Map<String,Object>findProvince(@RequestBody(required = false) String parm){
            try {
                String jsonData = addressService.findProvince(parm);
                return successMsg(jsonData);
            }catch (Exception e){
                return MsgTemplate.failureMsg(AddressMsgEnum.TIMEOUT_ERROR);
            }
    }
    /**
     * 查询城市   或查询已添加省/城市列表接口  或查询已添加省份下拉列表接口
     * @param parm
     * @return
     */
    @RequestMapping(name = "查询城市",value = "/address/findCity",method = {RequestMethod.POST})
    public Map<String,Object>findCity(@RequestBody(required = false) String parm){
        try {
                String jsonData = addressService.findCity(parm);
                return successMsg(jsonData);
        }catch (Exception e){
            return MsgTemplate.failureMsg(AddressMsgEnum.TIMEOUT_ERROR);
        }
    }

    /**
     * 查询区域
     * @param parm
     * @return
     */
    @RequestMapping(name = "查询区域",value = "/address/findDistrictc",method = {RequestMethod.POST})
    public Map<String,Object>findDistrictc(@RequestBody(required = false) String parm){
        try {
            String jsonData = addressService.findDistrictc(parm);
            return successMsg(jsonData);
        }catch (Exception e){
            return MsgTemplate.failureMsg(AddressMsgEnum.TIMEOUT_ERROR);
        }
    }

    /**
     * 查询街道
     * @param parm
     * @return
     */
    @RequestMapping(name = "查询街道",value = "/address/findStreet",method = {RequestMethod.POST})
    public Map<String,Object>findStreet(@RequestBody(required = false) String parm){
        try {
            String jsonData = addressService.findStreet(parm);
            return successMsg(jsonData);
        }catch (Exception e){
            return MsgTemplate.failureMsg(AddressMsgEnum.TIMEOUT_ERROR);
        }
    }
    /**
     * 修改城市以下的所有地址状态    删除列表接口   或者    添加接口
     * @param parm
     * @return
     */
    @RequestMapping(name = "修改城市以下的所有地址状态",value = "/address/updateAddressByCity",method = {RequestMethod.POST})
    public Map<String,Object>updateAddressByCity(@RequestBody(required = false) String parm){
        try {
            String jsonData = addressService.updateAddressByCity(parm);
            return successMsg(jsonData);
        }catch (Exception e){
            return MsgTemplate.failureMsg(AddressMsgEnum.TIMEOUT_ERROR);
        }
    }

    /**
     * 查询已添加的省份和城市列表
     * @param parm
     * @return
     */
    @RequestMapping(name = "查询已添加的省份和城市列表",value = "/address/listProvinceAndCity",method = {RequestMethod.POST})
    public Map<String,Object>listProvinceAndCity(@RequestBody(required = false) String parm){
        try {
            String jsonData = addressService.listProvinceAndCity(parm);
            return successMsg(jsonData);
        }catch (Exception e){
            return MsgTemplate.failureMsg(AddressMsgEnum.TIMEOUT_ERROR);
        }
    }
}
