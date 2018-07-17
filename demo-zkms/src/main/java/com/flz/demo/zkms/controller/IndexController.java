package com.flz.demo.zkms.controller;

import com.flz.demo.zkms.entity.ZkEntity;
import com.flz.demo.zkms.service.ZkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/main")
public class IndexController {

    @Autowired
    private ZkService zkService;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView model, HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession httpSession=request.getSession();
        Object sid=httpSession.getAttribute(httpSession.getId());
        model.addObject("CurRootName",sid);
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value ="/add")
    @ResponseBody
    public String addRootNode(@RequestParam(value="rootNodeName")String rootNode)
    {
        //用 “/” 分隔rootNode
        boolean ret=zkService.createRootNode(rootNode,"111111");
        return ret?"1":"0";
    }

    @RequestMapping("/alllist")
    @ResponseBody
    public List<String> getAllList(String rootName)
    {
        List<String> list=zkService.getList(rootName);
        return list;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<String> getList(String rootName,String version)
    {
        List<String> list=zkService.getList(String.format("%s/%s",rootName,version));
        return list;
    }
    @RequestMapping("/proplist")
    @ResponseBody
    public List<ZkEntity> getPropList(String nodePath)
    {
        List<ZkEntity> zkList=zkService.getPropertyList(nodePath);
        return zkList;
    }
}
