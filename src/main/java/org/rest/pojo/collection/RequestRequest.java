package org.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRequest extends RequestBase {
    private String url;

    public RequestRequest() { }

    public RequestRequest(String url, String method, String description, List<Header> headerList, Body body) {
        super(method, description, headerList, body);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
