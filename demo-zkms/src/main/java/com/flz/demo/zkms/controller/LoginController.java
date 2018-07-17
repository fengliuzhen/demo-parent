package com.flz.demo.zkms.controller;

import com.flz.demo.zkms.entity.ResultEntity;
import com.flz.demo.zkms.service.ZkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ZkService zkService;

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        HttpSession httpSession=request.getSession();
        httpSession.setAttribute(httpSession.getId(),"");
        model.addAttribute("imgr",(new Random().nextInt()));
        return "login";
    }

    @RequestMapping(value="/postlogin", method= RequestMethod.POST, consumes ="application/json")
    @ResponseBody
    public String postLogin(@RequestBody Map<String,String> mapParams, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try {
            String username = mapParams.get("username");
            String password = mapParams.get("password");
            String loginCode = mapParams.get("loginCode");
            //判断参数是否为空 String username,String password,String loginCode
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                return ResultEntity.JsonToStr(ResultEntity.ResultEntity(false, "1005", "登录名或密码不能为空"));
            }
            //限制用户登录 目前只能 caigouguanli 用户登录
            //if(!username.equals("caigouguanli"))
            //{
            //return JsonHelper.JsonResult(false,"1007",String.format("用户【%s】无权限登录",username)).toJSONString();
            //}
            //验证码
            //String tmpSessionId=CookieHelper.initLoginCookie(request,response);
            //String vcodeKey=SystemEnum.Valid_Code.getValue()+tmpSessionId;
            //String tmpCookie=redisService.get(vcodeKey);
            //if(tmpCookie==null||(!loginCode.toUpperCase().equals(tmpCookie.toUpperCase()))){
            //return JsonHelper.JsonResult(false,"1001","验证码不正确").toJSONString();
            //}
            //请求登录接口
            //LoginEntity adminEntity=userAPI.UserLogin(username, password);
            String pwd = zkService.getNodeVal(username);
            if (!Objects.equals(pwd.toUpperCase(), password.toUpperCase())) {
                return ResultEntity.JsonToStr(ResultEntity.ResultEntity(false, "1005", "密码不正确"));
            }
            //登录成功 移除验证码
            //redisService.delete(vcodeKey);
            //改为写radis  8个小时失效
            //redisService.put(SystemEnum.Admin_Sid.getValue()+tmpSessionId, adminEntity.getUserId(),8, TimeUnit.HOURS);
            //session记录用户信息
            HttpSession httpSession=request.getSession();
            httpSession.setMaxInactiveInterval(8*60*60);
            httpSession.setAttribute(httpSession.getId(),username);
            //记录日志
            logger.info(String.format("【%s】登录了系统。", username));
            //登录成功
            return ResultEntity.JsonToStr(ResultEntity.ResultEntity(true, "1000", "登录成功"));

        }
        catch(Exception ex)
        {
            System.out.print("登录异常："+ex.getMessage());
            return ResultEntity.JsonToStr(ResultEntity.ResultEntity(false,"1002","登录异常，请重新登录"));
        }
    }
}
