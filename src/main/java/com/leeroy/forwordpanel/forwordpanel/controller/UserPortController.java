package com.leeroy.forwordpanel.forwordpanel.controller;


import com.leeroy.forwordpanel.forwordpanel.common.WebCurrentData;
import com.leeroy.forwordpanel.forwordpanel.common.response.ApiResponse;
import com.leeroy.forwordpanel.forwordpanel.common.response.PageDataResult;
import com.leeroy.forwordpanel.forwordpanel.common.response.ResponseResult;
import com.leeroy.forwordpanel.forwordpanel.dto.UserPortPageRequest;
import com.leeroy.forwordpanel.forwordpanel.model.UserPort;
import com.leeroy.forwordpanel.forwordpanel.service.UserPortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: UserController
 * @Description: 系统用户管理
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/20 15:17
 */
@RestController
@RequestMapping("userport")
public class UserPortController {

    @Autowired
    private UserPortService userPortService;

    @PostMapping("getList")
    public ApiResponse getUserPortList(@RequestBody UserPortPageRequest pageRequest) {
        return ApiResponse.ok(userPortService.findUserPortList(pageRequest));
    }

    @PostMapping("save")
    public ApiResponse saveUserPort(@RequestBody List<UserPort> userPortList) {
        if(WebCurrentData.getUser().getUserType()>0){
            return ApiResponse.error("403", "您没有权限执行此操作");
        }
        return userPortService.save(userPortList);
    }

    @GetMapping("delete")
    public ApiResponse delete(Integer id) {
        if(WebCurrentData.getUser().getUserType()>0){
            return ApiResponse.error("403", "您没有权限执行此操作");
        }
        userPortService.delUserPort(id);
        return ApiResponse.ok();
    }

    /**
     * 禁用用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse disable(@RequestParam("id") Integer id) {
        if(WebCurrentData.getUser().getUserType()>0){
            return ApiResponse.error("403", "您没有权限执行此操作");
        }
        return userPortService.disablePort(id);
    }

    /**
     * 启用用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/enable", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse enable(@RequestParam("id") Integer id) {
        if(WebCurrentData.getUser().getUserType()>0){
            return ApiResponse.error("403", "您没有权限执行此操作");
        }
        return userPortService.enablePort(id);
    }


}
