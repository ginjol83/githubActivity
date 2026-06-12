package org.ginjol.githubactivity.models;

public class Activity {
    private String id  ;
    private String type ;
    private String repository;

    Activity (String id, String type, String repository){
        this.id         = id;
        this.type       = type;
        this.repository = repository;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }
}
