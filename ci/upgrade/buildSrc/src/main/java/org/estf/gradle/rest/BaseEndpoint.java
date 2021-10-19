package org.estf.gradle.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

public class BaseEndpoint {

    protected RestUtil utilLib;
    protected Instance instance;
    private final String endpointPath;
    private final HttpClient client;
    final private int MAX_RETRIES = 5;


    public BaseEndpoint(Instance instance, String endpointPath) {
        this.instance = instance;
        this.endpointPath = endpointPath;
        this.client = HttpClientBuilder.create().build();
        this.utilLib = new RestUtil();
    }

    protected void sendRequest(HttpEntityEnclosingRequestBase request, String message) throws IOException {
        try{
        for (int retries = 0; ; retries++) {
            HttpResponse response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            if (statusCode == 200) {
                break;
            }
            if (retries < MAX_RETRIES) {
                System.out.println("** Retrying to " + message + " **");
                Thread.sleep(5000);
            } else {
                throw new IOException("Failed to " + message);
            }

        }
        }catch(Exception e){}
    }
    protected String getBase64EncodedAuth(String username, String password) {
        String creds = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(creds.getBytes());
    }

    protected String getUrl() {
        return  (instance.esBaseUrl + endpointPath);
    }

    protected String getUrl(Map<String, String> pathParams) {
        System.out.println(instance.esBaseUrl);
        String url = this.getUrl();
        for (Map.Entry<String, String> entry: pathParams.entrySet()) {
            url = url.replace(entry.getKey(), entry.getValue());
        }
        return url;
    }
}
