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
 * Endpoint to retieve users from Cassandra DB
 *
 * Created by ramonriusgrasset on 11/11/14.
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


        Observable o = Observable.just(id).map(userId -> {

            Keyspace keyspace = CassandraContext.getContext().getClient();

            ColumnFamily<String, String> usersCf =
                    new ColumnFamily<String, String>("users",
                            StringSerializer.get(), StringSerializer.get(), StringSerializer.get());

            OperationResult<CqlResult<String, String>> operationResult = null;

            try {
                CqlQuery cqlQuery = keyspace.prepareQuery(usersCf).withCql("select * from ms_solution_b.users where id = ?;");
                operationResult = cqlQuery.asPreparedStatement().withIntegerValue(id).execute();
            } catch (ConnectionException e) {
                logger.error("Exception Querting to Cassandra", e);
                return Observable.error(e);
            }

            JSONObject content = new JSONObject();
            try {
                for (Row<String, String> row : operationResult.getResult().getRows()) {
                    JSONObject rowJson = new JSONObject();
                    rowJson.accumulate("id", row.getColumns().getColumnByName("id").getIntegerValue());
                    rowJson.accumulate("email", row.getColumns().getColumnByName("email").getStringValue());
                    rowJson.accumulate("name", row.getColumns().getColumnByName("name").getStringValue());
                    rowJson.accumulate("password", row.getColumns().getColumnByName("password").getStringValue());
                    rowJson.accumulate("surname", row.getColumns().getColumnByName("surname").getStringValue());
                    rowJson.accumulate("gender", row.getColumns().getColumnByName("gender").getStringValue());
                    content.accumulate("user", rowJson);
                }

            }catch (JSONException e){
                logger.error("Exception Querting to Cassandra", e);
                return Observable.error(e);
            }

            response.write(content.toString(), StringTransformer.DEFAULT_INSTANCE);
            return response.close();
        });


       return o;
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

        ColumnFamily<String, String> usersCf =
                new ColumnFamily<String, String>("users",
                        StringSerializer.get(), StringSerializer.get(), StringSerializer.get());

        OperationResult<CqlResult<String, String>> operationResult = null;
        try {
            operationResult = keyspace.prepareQuery(usersCf).withCql("select * from ms_solution_b.users;").execute();
        } catch (ConnectionException e) {
            logger.error("Exception Querting to Cassandra", e);
            return Observable.error(e);
        }

        JSONObject content = new JSONObject();
        try {
            for (Row<String, String> row : operationResult.getResult().getRows()) {
                JSONObject rowJson = new JSONObject();
                rowJson.accumulate("id", row.getColumns().getColumnByName("id").getIntegerValue());
                rowJson.accumulate("name", row.getColumns().getColumnByName("name").getStringValue());

                content.accumulate("users", rowJson);
            }
        }catch (JSONException e){
            logger.error("Exception Querting to Cassandra", e);
            return Observable.error(e);
        }

        response.write(content.toString(), StringTransformer.DEFAULT_INSTANCE);
        return response.close();
    }

}
