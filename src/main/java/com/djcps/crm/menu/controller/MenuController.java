package com.djcps.crm.menu.controller;

import com.djcps.crm.aop.inneruser.annotation.InnerUser;
import com.djcps.crm.aop.log.AddLog;
import com.djcps.crm.commons.config.ParamsConfig;
import com.djcps.crm.commons.msg.MsgTemplate;
import com.djcps.crm.inneruser.model.InnerUserModel;
import com.djcps.crm.menu.enums.MenuMsgEnum;
import com.djcps.crm.menu.model.Button;
import com.djcps.crm.menu.model.Menu;
import com.djcps.crm.menu.server.MenuHttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rpc.plugin.http.HTTPResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.djcps.crm.commons.utils.DJGson.gson;

/**
 * 菜单
 * Created by lk on 2017-07-06.
 */
@Controller
@RequestMapping("/menu")
@ResponseBody
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuHttpServer menuServer;


    private final int fatherId = ParamsConfig.MENU_FATHER_ID;

    /**
     * 该接口用于获取用户所拥有权限的菜单
     * token
     *
     * @param innerUser the innerUser
     * @return map
     */
    @RequestMapping(name = "获取所有菜单",value = "/allMenu",method = RequestMethod.POST)
    @AddLog(module = "菜单模块",value = "获取所有菜单")
    public Map<String, Object> allMenu(@InnerUser InnerUserModel innerUser) {
        try {
            int userId = innerUser.getId();
            //26,81
            HTTPResponse httpResponse = menuServer.getAllMenu(fatherId,userId);
            if(httpResponse.isSuccessful()){
                String menus = httpResponse.getBodyString();
                System.out.println(menus);
                Menu menu = gson.fromJson(menus,Menu.class);
                if(menu.isSuccess()){
                    return MsgTemplate.successMsg(menu.getData());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取菜单失败: " + e.getCause());
        }
        return MsgTemplate.failureMsg(MenuMsgEnum.MENUS_INFO_ERROR);
    }

    /**
     * 该接口用于获取用户所拥有权限的按钮
     * token
     *
     * @param innerUser the innerUser
     * @return map
     */
    @RequestMapping(name = "获取所有按钮",value = "/allButton",method = RequestMethod.POST)
    @AddLog(module = "菜单模块",value = "获取所有按钮")
    public Map<String, Object> allButton(@InnerUser InnerUserModel innerUser) {
        try {
            int userId = innerUser.getId();
            HTTPResponse httpResponse = menuServer.getAllButton(fatherId,userId);
            if(httpResponse.isSuccessful()){
                String buttons = httpResponse.getBodyString();
                Button button = gson.fromJson(buttons,Button.class);
                if(button.isSuccess()){
                    return MsgTemplate.successMsg(button.getData());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取菜单失败: " + e.getCause());
        }
        return MsgTemplate.failureMsg(MenuMsgEnum.MENUS_INFO_ERROR);
    }


}
