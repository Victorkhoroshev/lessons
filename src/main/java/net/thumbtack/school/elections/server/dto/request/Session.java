package net.thumbtack.school.elections.server.dto.request;


public class Session {
    private String token;

    public Session(String token) {
        setToken(token);
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
