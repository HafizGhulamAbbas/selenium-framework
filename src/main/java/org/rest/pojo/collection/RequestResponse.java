package org.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResponse extends RequestBase {
    URL url;

    public RequestResponse() { }

    public RequestResponse(URL url, String method, String description, List<Header> headerList, Body body) {
        super(method, description, headerList, body);
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
