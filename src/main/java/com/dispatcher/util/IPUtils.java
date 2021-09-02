package com.dispatcher.util;

import java.net.*;
import java.util.Enumeration;

/**
 * @Author think
 * @Date 2021/8/16 15:53
 * @Version 1.0
 */
public class IPUtils {
    private static void getIpAddressByNetworkInterface() {
        try {
            Enumeration nets = NetworkInterface.getNetworkInterfaces();
            NetworkInterface net;
            InetAddress inetAddress;
            while (nets.hasMoreElements()) {
                net = (NetworkInterface) nets.nextElement();
                Enumeration address = net.getInetAddresses();
                while (address.hasMoreElements()) {
                    inetAddress = (InetAddress) address.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) {
                        System.out.println(inetAddress.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        getIpAddressByNetworkInterface();
        System.out.println(getLocalIP());
    }

    public static String getLocalIP() {
        String localIP = "127.0.0.1";
        InetAddress addr = null;
        try {
            addr = (InetAddress) InetAddress.getLocalHost();
            //获取本机IP
            localIP = addr.getHostAddress().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return localIP;
    }
}
