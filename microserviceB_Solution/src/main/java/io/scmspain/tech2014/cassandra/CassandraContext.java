package io.scmspain.tech2014.cassandra;

import com.google.inject.Singleton;
import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.connectionpool.NodeDiscoveryType;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl;
import com.netflix.astyanax.connectionpool.impl.CountingConnectionPoolMonitor;
import com.netflix.astyanax.impl.AstyanaxConfigurationImpl;
import com.netflix.astyanax.thrift.ThriftFamilyFactory;

/**
 * Class holding the Cassandra's AstyanaxContext so it can be retrieved from anywhere
 *
 * Created by ramonriusgrasset on 11/11/14.
 */
public class CassandraContext {

    private static AstyanaxContext<Keyspace> context;

    /**
     * Returns the Astyanax Cassandra Context singleton
     * @return AstyanaxContext
     */
    public static AstyanaxContext<Keyspace> getContext(){
        if(context == null){
            synchronized (CassandraContext.class){
                if(context == null){
                    context = initializeCassandraContext();
                }
            }
        }
        return context;
    }

    /**
     * Initializes the AstyanaxContext
     * @return AstyanaxContext
     */
    public static AstyanaxContext<Keyspace> initializeCassandraContext(){
        AstyanaxContext<Keyspace> context = new AstyanaxContext.Builder()
                .forCluster("ClusterName")
                .forKeyspace("ms_solution_b")
                .withAstyanaxConfiguration(new AstyanaxConfigurationImpl()
                                .setDiscoveryType(NodeDiscoveryType.RING_DESCRIBE)
                                .setCqlVersion("3.0.0")
                                .setTargetCassandraVersion("2.1.2")
                )
                .withConnectionPoolConfiguration(new ConnectionPoolConfigurationImpl("MyConnectionPool")
                                .setPort(9160)
                                .setMaxConnsPerHost(1)
                                .setSeeds("127.0.0.1:9160")
                )
                .withConnectionPoolMonitor(new CountingConnectionPoolMonitor())
                .buildKeyspace(ThriftFamilyFactory.getInstance());



        context.start();

        return context;
    }

}
