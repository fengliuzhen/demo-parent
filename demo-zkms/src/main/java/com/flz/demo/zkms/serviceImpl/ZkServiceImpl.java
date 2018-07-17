package com.flz.demo.zkms.serviceImpl;

import com.flz.demo.zkms.entity.ZkEntity;
import com.flz.demo.zkms.service.ZkService;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ZkServiceImpl implements ZkService {

    private static final Logger log = LoggerFactory.getLogger(ZkServiceImpl.class);
    //zk地址
    @Value("${zk-url}")
    private String zkUrl;
    //zookeeper操作类
    private CuratorFramework curatorFramework;

    @PostConstruct
    private void init() {
        RetryPolicy retryPolicy=new ExponentialBackoffRetry(1000,3);
        curatorFramework = CuratorFrameworkFactory.newClient(zkUrl,retryPolicy);
        curatorFramework.start();
    }

    @PreDestroy
    private void destroy() {
        if (curatorFramework != null) {
            curatorFramework.close();
        }
    }


    @Override
    public boolean createRootNode(String rootNode) {
        return createRootNode(rootNode,null);
    }

    @Override
    public boolean createRootNode(String rootNode, String value) {
        log.debug("create node:{},value:{}",rootNode,value);
        boolean isSuccess=false;
        try {
            Stat stat=curatorFramework.checkExists().forPath(rootNode);
            if(Objects.equals(stat,null))
            {
                String retNode;
                if(Strings.isNullOrEmpty(value)) {
                    retNode=curatorFramework.create().creatingParentsIfNeeded().forPath(rootNode);
                }
                else {
                    final byte[] data = Strings.isNullOrEmpty(value) ? new byte[]{} : value.getBytes(Charsets.UTF_8);
                    retNode=curatorFramework.create().creatingParentsIfNeeded().forPath(rootNode, data);
                }
                isSuccess=Objects.equals(retNode,rootNode);
            }
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage(),ex);
        }
        return isSuccess;
    }

    @Override
    public List<String> getList(String nodeName) {
        log.debug("find {} children",nodeName);
        List<String> childList=null;
        try {
            Stat stat=curatorFramework.checkExists().forPath(nodeName);
            if(!Objects.equals(stat,null))
            {
                childList=curatorFramework.getChildren().forPath(nodeName);
            }
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage(),ex);
        }
        if(!Objects.equals(childList,null)&&childList.size()>0)
        {
            childList.remove("zookeeper");
        }
        return childList;
    }

    @Override
    public String getNodeVal(String rootNode) {
        log.debug("getNodeVal {}",rootNode);
        try {
            // 判断节点是否存在
            Stat stat = curatorFramework.checkExists().forPath(rootNode);
            if (stat != null) {
                byte[] data = curatorFramework.getData().forPath(rootNode);
                return new String(data);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    @Override
    public List<ZkEntity> getPropertyList(String nodeName) {
        List<String> keyList=getList(nodeName);
        List<ZkEntity> zkEntities=new ArrayList<>();
        for(String str:keyList)
        {
            ZkEntity zkEntity=new ZkEntity();
            zkEntity.setKey(str);
            zkEntity.setValue(getNodeVal(nodeName+"/"+str));
            zkEntities.add(zkEntity);
        }
        return zkEntities;
    }

    @Override
    public boolean deleteProperty(String propPath) {
        log.debug("Delete property: [{}]", propPath);
        try {
            Stat stat = curatorFramework.checkExists().forPath(propPath);
            if (stat != null) {
                curatorFramework.delete().deletingChildrenIfNeeded().forPath(propPath);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateProperty(String propPath, String value) {
        log.debug("Update property: [{}] = [{}]", propPath, value);
        boolean suc = false;
        try {
            Stat stat = curatorFramework.checkExists().forPath(propPath);
            if (stat != null) {
                Stat opResult = curatorFramework.setData().forPath(propPath, value.getBytes(Charsets.UTF_8));
                suc = opResult != null;
            } else {
                String opResult = curatorFramework.create().creatingParentsIfNeeded().forPath(propPath, value.getBytes(Charsets.UTF_8));
                suc = Objects.equals(propPath, opResult);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return suc;
    }
}
