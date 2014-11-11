package io.scmspain.tech2014.server.simple.annotation;

import com.google.inject.Singleton;
import com.netflix.adminresources.resources.KaryonWebAdminModule;
import com.netflix.governator.annotations.Modules;
import com.netflix.karyon.KaryonBootstrap;
import com.netflix.karyon.archaius.ArchaiusBootstrap;
import com.netflix.karyon.eureka.KaryonEurekaModule;
import com.netflix.karyon.servo.KaryonServoModule;
import com.netflix.karyon.transport.http.KaryonHttpModule;
import io.netty.buffer.ByteBuf;
import io.scmspain.tech2014.common.LoggingInterceptor;
import io.scmspain.tech2014.common.health.HealthCheck;
import io.scmspain.tech2014.server.simple.SimpleRouter;


/**
 * @author Tomasz Bak
 */
@ArchaiusBootstrap
@KaryonBootstrap(name = "hello-netflix-oss", healthcheck = HealthCheck.class)
@Singleton
@Modules(include = {
        //ShutdownModule.class,
        KaryonServoModule.class,
        KaryonWebAdminModule.class,
        KaryonEurekaModule.class, // Uncomment this to enable Eureka client.
        SimpleRoutingApp.KaryonRxRouterModuleImpl.class
})
public interface SimpleRoutingApp {

    class KaryonRxRouterModuleImpl extends KaryonHttpModule<ByteBuf, ByteBuf> {

        public KaryonRxRouterModuleImpl() {
            super("httpServerA", ByteBuf.class, ByteBuf.class);
        }

        @Override
        protected void configureServer() {
            bindRouter().toInstance(new SimpleRouter());

            interceptorSupport().forUri("/*").intercept(LoggingInterceptor.class);

            server().port(8888);
        }
    }
}
