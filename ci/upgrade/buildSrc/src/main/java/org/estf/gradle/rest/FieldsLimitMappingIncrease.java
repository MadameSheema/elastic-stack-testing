package org.estf.gradle.rest;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;

public class FieldsLimitMappingIncrease extends BaseEndpoint {

    private final String body;
    private final String index;
    private final String message = "increase the number of fields limit in mapping";
    private static final String endpointPath = "/{index}/_settings";


    public FieldsLimitMappingIncrease(Instance instance, String index ) {
        super(instance, endpointPath);
        this.body = "{\"index.mapping.total_fields.limit\":2000}";
        this.index = index;
    }

    public void sendPutRequest() throws IOException {
        sendRequest(utilLib.getPutEntityForString(getUrl(new HashMap<String, String>() {{ put("{index}", index); }}), getBase64EncodedAuth(instance.username, instance.password), body), message);
    }
}
