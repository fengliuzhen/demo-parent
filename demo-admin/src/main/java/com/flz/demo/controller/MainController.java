package com.flz.demo.controller;

import com.flz.demo.common.ClientUtils;
import com.flz.demo.common.ServerUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController extends BaseController {

    @RequestMapping("/test")
    public String testRequest()
    {
        String id=request.getParameter("id");
        return id+"_1_"+ServerUtils.getHostName();
    }
    @RequestMapping("/test2")
    public String testRequest2()
    {
        String id=request.getParameter("id");
        return id+"_2_"+ClientUtils.getIp();
    }
}
