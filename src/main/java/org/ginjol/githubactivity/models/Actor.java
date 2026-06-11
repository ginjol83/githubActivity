package org.ginjol.githubactivity.models;

public class Actor {
    private String id;
    private String url;
    private String login;
    private String avatar_url;
    private String gravatar_id;
    private String display_login;

    public Actor(){    }

    public Actor(String actorId, String actorLogin, String actorDisplayLogin, String actorGravatarId, String actorUrl, String actorAvatarUrl) {
        this.id             = actorId;
        this.url            = actorUrl;
        this.login          = actorLogin;
        this.avatar_url     = actorAvatarUrl;
        this.gravatar_id    = actorGravatarId;
        this.display_login  = actorDisplayLogin;
    }

    public String getId() {
        return id;
    }

    public void setId(String login) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplay_login() {
        return display_login;
    }

    public void setDisplay_login(String display_login) {
        this.display_login = display_login;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
