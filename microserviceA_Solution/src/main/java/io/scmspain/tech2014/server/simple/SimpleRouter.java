package io.scmspain.tech2014.server.simple;

import com.netflix.karyon.transport.http.SimpleUriRouter;
import com.netflix.karyon.transport.http.health.HealthCheckEndpoint;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import io.scmspain.tech2014.common.health.HealthCheck;
import rx.Observable;


public class SimpleRouter implements RequestHandler<ByteBuf, ByteBuf> {

    private final SimpleUriRouter<ByteBuf, ByteBuf> delegate;

    public SimpleRouter() {
        final FriendsEndpoint friendsEndpoint = new FriendsEndpoint();

        //Instantiate the AddEndpoint and asign it to a local variable


        delegate = new SimpleUriRouter<ByteBuf, ByteBuf>();

        delegate.addUri("/healthcheck", new HealthCheckEndpoint(new HealthCheck()))

                .addUri("/friends/*",
                        new RequestHandler<ByteBuf, ByteBuf>() {
                            @Override
                            public Observable<Void> handle(
                                    HttpServerRequest<ByteBuf> request,
                                    HttpServerResponse<ByteBuf> response) {
                                return friendsEndpoint.getFriends(request, response);
                            }
                        });//Remove this semicolon and add the binding for requests to "/add/*"
    }

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        return delegate.handle(request, response);
    }
}
