package com.vult.clipboard.config.handler;

import com.vult.clipboard.common.utils.IpAddressUtils;
import com.vult.clipboard.mapper.WhiteListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Songyi Wang
 * @Email: wangsongyi08@gmail.com
 * @Date: 5/9/2020 12:59 PM
 */

@Service
public class CustomizeAccessDecisionManager implements AccessDecisionManager {
    private static Logger logger = LoggerFactory.getLogger(CustomizeAccessDecisionManager.class);

    @Autowired
    private WhiteListMapper whiteListMapper;

    @Override
    public void decide(Authentication authentication,
                       Object o,
                       Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        String ipAddr = request.getRemoteHost();
        List<String> whiteList = whiteListMapper.getAll();
        if (IpAddressUtils.CheckAcceptance(ipAddr, whiteList)) {
            logger.info("Request from ip address: " + ipAddr + ", ALLOWED");
            return;
        }
        logger.info("Request from ip address: " + ipAddr + ", REJECTED");
        throw new AccessDeniedException("No permission for your ip address");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
