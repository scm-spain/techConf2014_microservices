package io.scmspain.tech2014.server.simple;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.channel.StringTransformer;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.scmspain.tech2014.repo.ItemsRepo;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.List;
public class FriendsEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(AddEndpoint.class);


    public Observable<Void> getFriends(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {

        int prefixLength = "/friends/".length();
        String email = request.getPath().substring(prefixLength);

        List<String> friends = ItemsRepo.getFriendsFor(email);

        JSONObject content = new JSONObject();

        try {
            if(friends!=null){
                content.put("friends", friends);
            }else{
                content.put("friends", email + " has no friends :(");
            }
        } catch (Exception e) {
            logger.error("Error creating json response.", e);
            throw new RuntimeException("Fuck!!", e);
        }

        response.write(content.toString(), StringTransformer.DEFAULT_INSTANCE);
        return response.close();

    }
}
