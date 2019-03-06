package com.asierg.wsserver.ws.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

@Component
@Slf4j
public class GlobalEndpointInterceptor implements EndpointInterceptor {

    @Override
    public boolean handleRequest(MessageContext messageContext, Object o) throws Exception {
        log.info("Global Request Handling");
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object o) throws Exception {
        log.info("Global Response Handling");
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object o) throws Exception {
        log.info("Global Exception Handling");
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object o, Exception e) throws Exception {
        log.info("Execute Code After Completion");
    }
}
