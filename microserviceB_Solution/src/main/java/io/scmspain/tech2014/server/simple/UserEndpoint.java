package io.scmspain.tech2014.server.simple;

import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.connectionpool.OperationResult;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.model.CqlResult;
import com.netflix.astyanax.model.Row;
import com.netflix.astyanax.query.CqlQuery;
import com.netflix.astyanax.serializers.StringSerializer;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.channel.StringTransformer;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.scmspain.tech2014.cassandra.CassandraContext;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;


/**
 * Endpoint to retrieve users from Cassandra DB
 */
public class UserEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(UserEndpoint.class);

    /**
     * Retrieves the user identified by the email passed in the URL in /user/{email}
     *
     * @param request
     * @param response
     * @return Observable
     */
    public Observable<Void> getUser(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response){

        int prefixLength = "/user/".length();
        Integer id = Integer.parseInt(request.getPath().substring(prefixLength));

        Keyspace keyspace = CassandraContext.getContext().getClient();

        //Code


        return response.close();
    }

    /**
     * Retrieves all users in table Users
     *
     * @param request
     * @param response
     * @return Observable
     */
    public Observable<Void> getUsers(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response){


        Keyspace keyspace = CassandraContext.getContext().getClient();

        //Code


        return response.close();
    }

}
