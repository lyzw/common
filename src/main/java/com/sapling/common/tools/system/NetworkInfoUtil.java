package com.sapling.common.tools.system;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网络相关信息
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/6/9.
 * @since v1.0
 */
public class NetworkInfoUtil {

    private static Logger logger = LoggerFactory.getLogger(NetworkInfoUtil.class);

    public static String getHostIp() throws UnknownHostException {
        String ip = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while(networkInterfaces.hasMoreElements()){
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress address = addresses.nextElement();
                    logger.debug(address.getHostAddress());
                    if (address != null && !address.isAnyLocalAddress()
                            && !address.isLoopbackAddress() && address instanceof Inet4Address){
                        ip = address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static String getHostName() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostName();
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(System.getProperties());
        System.out.println(System.getenv());
        System.out.println(getHostName());
    }


}
