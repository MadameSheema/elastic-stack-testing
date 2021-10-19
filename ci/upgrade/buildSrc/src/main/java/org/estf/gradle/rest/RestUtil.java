package org.estf.gradle.rest;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.File;

public class RestUtil {
    public HttpPost getPostEntityForFile(String url, String authHeader, String filepath) {
        HttpPost postRequest = new HttpPost(url);
        postRequest.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
      //  postRequest.setEntity(new FileEntity(new File(filepath),
       //         ContentType.create(APPLICATION_NDJSON)));
        return postRequest;
    }

    public HttpPut getPutEntityForString(String url, String authHeader, String jsonStr)  {
        HttpPut putRequest = new HttpPut(url);
        putRequest.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        try{
            StringEntity entity = new StringEntity(jsonStr);
            entity.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            putRequest.setEntity(entity);
        } catch(Exception e){ }

        return putRequest;
    }
}
