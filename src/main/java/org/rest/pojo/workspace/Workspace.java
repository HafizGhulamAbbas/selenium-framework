package org.rest.pojo.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;

public class Workspace {
    @JsonIgnore
    private int i;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonIgnore
    private HashMap<String, String> myHashMap;

    private String name;
    private String type;
    private String description;

    public Workspace() { }

    public Workspace(String name, String type, String description){
        this.name = name;
        this.type = type;
        this.description = description;
    }
    @JsonIgnore
    public int getI() { return i; }
    @JsonIgnore
    public void setI(int i) { this.i = i; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    @JsonIgnore
    public HashMap<String, String> getMyHashMap() { return myHashMap; }
    @JsonIgnore
    public void setMyHashMap(HashMap<String, String> myHashMap) { this.myHashMap = myHashMap; }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
