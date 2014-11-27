package io.scmspain.tech2014.server.simple;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.channel.StringTransformer;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.List;
import java.util.Map;

public class AddEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(AddEndpoint.class);


    public Observable<Void> add(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        JSONObject content = new JSONObject();

        response.write(content.toString(), StringTransformer.DEFAULT_INSTANCE);
        return response.close();

    }


}
