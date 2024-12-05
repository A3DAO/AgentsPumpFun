package com.agent.common.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpRequestUtil
 *
 * @author lll
 */
public class HttpRequestUtil {

    private final static String PROXY_FLAG = "X-Forwarded-For";
    private static final String CURRENT_LOGIN_CLIENT_IOS = "IOS";
    private static final String CURRENT_LOGIN_CLIENT_ANDROID = "ANDROID";
    private static final String CURRENT_LOGIN_CLIENT_WEB = "PC_WEB";
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static boolean isMicroMessenger(HttpServletRequest request) {
        return request.getHeader("User-Agent").toLowerCase().contains("micromessenger");
    }

    public static boolean isAndroid(HttpServletRequest request) {
        return request.getHeader("User-Agent").toLowerCase().contains("app - android");
    }

    public static boolean isIOS(HttpServletRequest request) {
        return request.getHeader("User-Agent").toLowerCase().contains("app - ios");
    }

    public static boolean isBrowserIPad(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        return !(HttpRequestUtil.isIOS(request)) && userAgent.contains("ipad");
    }

    public static boolean isBrowserIPhone(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        return !(HttpRequestUtil.isIOS(request)) && (userAgent.contains("iphone") || userAgent.contains("ipod"));
    }

    public static String buildRequestPath(HttpServletRequest request) {
        return StringUtils.isBlank(request.getQueryString()) ? request.getServletPath()
                : request.getServletPath() + "?" + request.getQueryString();
    }

    public static String buildBasePath(HttpServletRequest request) {
        String scheme = request.getHeader("x-forwarded-proto");
        if (StringUtils.isBlank(scheme)) {
            scheme = request.getScheme();
        }
        return scheme + "://" + request.getServerName()
                + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort());
    }

    public static String buildSiteBasePath(HttpServletRequest request) {
        return buildBasePath(request) + request.getContextPath();
    }

    public static String getClientIpAddress(HttpServletRequest request) {
        Enumeration<String> enumeration = request.getHeaderNames();
        String ipAddress = null;
        String ip = null;
        while (enumeration.hasMoreElements()) {
            String paraName = enumeration.nextElement();
            if (PROXY_FLAG.equalsIgnoreCase(paraName)) {
                ipAddress = request.getHeader(paraName);
                break;
            }
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            // HTTP_CLIENT_IP：有些代理服务器
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            // X-Real-IP：nginx服务代理
            ipAddress = request.getHeader("X-Real-IP");
        }

        // 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddress != null && ipAddress.length() != 0) {
            ip = ipAddress.split(",")[0];
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 获取登录客户端类型
     */
    public static String getClientType(HttpServletRequest request) {
        String clientType = request.getHeader("User-Agent");
        if (!StringUtils.isBlank(clientType)) {
            if (HttpRequestUtil.isAndroid(request)) {
                clientType = CURRENT_LOGIN_CLIENT_ANDROID;
            } else if (HttpRequestUtil.isBrowserIPhone(request)) {
                clientType = CURRENT_LOGIN_CLIENT_IOS;
            } else {
                clientType = CURRENT_LOGIN_CLIENT_WEB;
            }
        } else {
            clientType = CURRENT_LOGIN_CLIENT_WEB;
        }
        return clientType;
    }
}
