package com.oshippa;

import com.oshippa.common.exception.AccessDeniedExceptionMapper;
import com.oshippa.auth.filter.jersey.JerseyCrossOriginResourceSharingFilter;
import com.oshippa.auth.resource.GenericExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.ws.rs.container.ContainerResponseFilter;

/**
 * Created by iainporter on 28/07/2014.
 */
public class RestResourceApplication extends ResourceConfig {

    public RestResourceApplication() {

        packages("com.oshippa.auth.user.resource", "com.oshippa.auth.resource",
        "com.oshippa.auth.sample","com.oshippa.server.controller");

        register(RequestContextFilter.class);

        ApplicationContext rootCtx = ContextLoader.getCurrentWebApplicationContext();
        ContainerResponseFilter filter = rootCtx.getBean(JerseyCrossOriginResourceSharingFilter.class);
        register(filter);
        property(ServerProperties.WADL_FEATURE_DISABLE,true);
        register(GenericExceptionMapper.class);
        register(AccessDeniedExceptionMapper.class);

        register(JacksonFeature.class);

    }
}
