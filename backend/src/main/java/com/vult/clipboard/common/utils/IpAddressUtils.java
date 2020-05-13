package com.vult.clipboard.common.utils;

import java.util.List;

/**
 * @Author: Songyi Wang
 * @Email: wangsongyi08@gmail.com
 * @Date: 5/9/2020 2:30 PM
 */
public class IpAddressUtils {
    public static boolean CheckAcceptance(String ipAddr, List<String> whiteList) {
        for (String ip: whiteList) {
            if (ipAddr.startsWith(ip))
                return true;
        }
        return false;
    }
}
