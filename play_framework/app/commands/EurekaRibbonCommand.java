package commands;

import com.netflix.appinfo.CloudInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.RetryHandler;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.loadbalancer.*;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import rx.Observable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EurekaRibbonCommand {

    private final ILoadBalancer loadBalancer;
    // retry handler that does not retry on same server, but on a different server
    private final RetryHandler retryHandler = new DefaultLoadBalancerRetryHandler(0, 1, true);

    public EurekaRibbonCommand(String vipAdress) {
        List<InstanceInfo> instanceInfos = DiscoveryManager
                .getInstance()
                .getDiscoveryClient()
                .getInstancesByVipAddress(vipAdress, false);
        List<Server> serverList = new ArrayList<Server>();

        for(InstanceInfo instance : instanceInfos){
            serverList.add(new Server(instance.getHostName(), instance.getPort()));
        }

        loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);
    }

    public String call(final String action, final String queryString, final String verb) throws Exception {
            return LoadBalancerCommand.<String>builder()
                .withLoadBalancer(loadBalancer)
                .withRetryHandler(retryHandler)
                .build()
                .submit(server -> {
                    try {
                        String uri = "http://" + server.getHost() + ":" + server.getHostPort() + "/" + getUri(action, queryString);
                        URL url = new URL(uri);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod(verb);
                        return Observable.just(getContent(conn));
                    } catch (Exception e) {
                        return Observable.error(e);
                    }
                }).toBlocking().first();
    }

    private String getUri(String action, String queryString){
        String query = "";
        if(queryString != null && !queryString.isEmpty()){
            query = "?" + queryString;
        }
        return  action + query;
    }
    private String getContent(HttpURLConnection connection){
        try {
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            bufferReader.close();
            return stringBuilder.toString();
        } catch (IOException exception){
            return "";
        }


    }


}