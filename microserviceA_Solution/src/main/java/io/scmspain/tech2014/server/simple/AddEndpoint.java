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
        try {
            Map<String, List<String>> params = request.getQueryParameters();
            int addend1 = Integer.parseInt(params.get("addend1").get(0));
            int addend2 = Integer.parseInt(params.get("addend2").get(0));
            content.put("result", addend1 + addend2);

        } catch (Exception e) {
            logger.error("Error creating json response.", e);
            return Observable.error(e);
        }

        response.write(content.toString(), StringTransformer.DEFAULT_INSTANCE);
        return response.close();

    }


}
