package com.flz.demo.zkms.service;

import com.flz.demo.zkms.entity.ZkEntity;

import java.util.List;

public interface ZkService {

    boolean createRootNode(String rootNode);
    boolean createRootNode(String rootNode,String value);
    List<String> getList(String nodeName);
    String getNodeVal(String rootNode);
    List<ZkEntity> getPropertyList(String nodeName);
    boolean deleteProperty(String propPath);
    boolean updateProperty(String propPath,String value);
}
