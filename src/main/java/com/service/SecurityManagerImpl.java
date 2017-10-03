package com.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.StringTokenizer;

@Component
public class SecurityManagerImpl implements SecurityManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityManagerImpl.class);

    @Override
    public boolean isAuthorized(String action, String authString) {
        if (StringUtils.isEmpty(authString)) {
            return false;
        }
        String[] authParts = authString.split("\\s+");
        String authInfo;
        if ((authParts.length == 2) && (authParts[0].equalsIgnoreCase("Basic"))) {
            authInfo = authParts[1];
        } else {
            LOGGER.error("Error decoding authorization string: {}", authString);
            return false;
        }
        // Decode data
        LOGGER.info("Encoded auth: {}", authInfo);
        String decodedAuth;
        try {
            decodedAuth = new String(new BASE64Decoder().decodeBuffer(authInfo));
        } catch (IOException e) {
            LOGGER.error("Error decoding authorization string: {}", e.getMessage());
            return false;
        }
        LOGGER.info("Decoded auth: {}", decodedAuth);
        final StringTokenizer tok = new StringTokenizer(decodedAuth, ":");
        return isAuthorized(action, tok.nextToken(), tok.nextToken());
    }

    private boolean isAuthorized(String action, String user, String pass) {
        LOGGER.info("User: '{}'", user);
        LOGGER.info("Pass: '{}'", pass);
        LOGGER.info("Action: {}", action);
        return "Aladdin".equals(user) && "open sesame".equals(pass);
    }

}
