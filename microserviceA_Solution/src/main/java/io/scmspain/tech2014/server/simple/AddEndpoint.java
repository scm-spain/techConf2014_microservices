package io.scmspain.tech2014.server.simple;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.channel.StringTransformer;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(AddEndpoint.class);


    /**
     * Reactive endpoint that su8ms two given numbers and writes
     * the result to the response in JSON format
     * @param request
     * @param response
     * @return
     */
    public Observable<Void> add(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        Observable o = getAddEnds(request).reduce((a,b) -> a + b).flatMap(a -> {
            JSONObject content = new JSONObject();
            try{
                content.put("result", a);
            } catch (Exception e) {
                logger.error("Error creating json response.", e);
                return Observable.error(e);
            }
            response.write(content.toString(), StringTransformer.DEFAULT_INSTANCE);
            return response.close();
        });
        return o;
    }

    /**
     * Returns a List with the values to sum
     * @param request
     * @return
     */
    private Observable<Integer> getAddEnds(HttpServerRequest<ByteBuf> request){
        Map<String, List<String>> params = request.getQueryParameters();
        Integer addend1 = Integer.parseInt(params.get("add1").get(0));
        Integer addend2 = Integer.parseInt(params.get("add2").get(0));

        List<Integer> addEnds = new ArrayList<>();
        addEnds.add(addend1);
        addEnds.add(addend2);
        return Observable.from(addEnds);
    }


}
