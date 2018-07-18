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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/main")
public class IndexController extends BaseController {

    @Autowired
    private ZkService zkService;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView model)
    {
        HttpSession httpSession=request.getSession();
        Object sid=httpSession.getAttribute(httpSession.getId());
        model.addObject("CurRootName",sid);
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value ="/add")
    @ResponseBody
    public String addNode(@RequestParam(value="rootNodeName")String rootNode)
    {
        //用 “/” 分隔rootNode
        boolean ret=zkService.createRootNode(rootNode,null);
        return ret?"1":"0";
    }
    @RequestMapping(value ="/addroot")
    @ResponseBody
    public String addRootNode(@RequestParam(value="rootNodeName")String rootNode)
    {
        //用 “/” 分隔rootNode
        boolean ret=zkService.createRootNode(rootNode,"111111");
        return ret?"1":"0";
    }
    @RequestMapping("/deflist")
    @ResponseBody
    public List<String> getDefList()
    {
        HttpSession httpSession=request.getSession();
        Object sid=httpSession.getAttribute(httpSession.getId());
        if(Objects.equals(sid.toString().toUpperCase(),"ADMIN")) {
            sid = "/";
            List<String> list = zkService.getList(sid.toString());
            return list;
        }
        else
        {
            List<String> list =new ArrayList<>();
            list.add(sid.toString().substring(1));
            return list;
        }
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
    @RequestMapping(value ="/del")
    @ResponseBody
    public String delRootNode(@RequestParam(value="nodeName")String nodeName)
    {
        //安全删除属性
        boolean ret=zkService.deleteProperty(nodeName);
        return ret?"1":"0";
    }
    @RequestMapping(value ="/update")
    @ResponseBody
    public String updateRootNode(@RequestParam(value="nodeName")String nodeName,@RequestParam(value="value")String value)
    {
        //修改属性值 ，如果属性节点不存在 重新创建
        boolean ret=zkService.updateProperty(nodeName,value);
        return ret?"1":"0";
    }
}
