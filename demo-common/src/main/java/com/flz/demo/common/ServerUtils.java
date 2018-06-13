package com.flz.demo.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 服务器工具类
 */
public class ServerUtils {
    /**
     * 获取服务器名称
     * @return
     */
    public static String getHostName()
    {
        if (System.getenv("COMPUTERNAME") != null) {
            return System.getenv("COMPUTERNAME");
        }
        else {
            return getHostNameEx();
        }
    }
    private static String getHostNameEx() {
        try {
            return (InetAddress.getLocalHost()).getHostName();
        }
        catch (UnknownHostException uhe) {
            String host = uhe.getMessage(); // host = "hostname: hostname"
            if (host != null) {
                int colon = host.indexOf(':');
                if (colon > 0) {
                    return host.substring(0, colon);
                }
            }
            return "UnknownHost";
        }
    }
}
