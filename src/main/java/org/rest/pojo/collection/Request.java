package org.rest.pojo.collection;

import java.util.List;

public class Request {
    private String url;
    private String method;
    private String description;
    List<Header> headerList;
    Body body;
    public Request() { }
    public Request(String url, String method, String description, List<Header> headerList, Body body) {
        this.url = url;
        this.method = method;
        this.description = description;
        this.headerList = headerList;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Header> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<Header> headerList) {
        this.headerList = headerList;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
