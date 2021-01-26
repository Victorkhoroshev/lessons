package net.thumbtack.school.elections.server.dto.request;

public class TakeIdeaDtoRequest {
    private String token;
    private String ideaKey;

    public TakeIdeaDtoRequest(String token, String ideaKey) {
        setToken(token);
        setIdeaKey(ideaKey);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdeaKey() {
        return ideaKey;
    }

    public void setIdeaKey(String ideaKey) {
        this.ideaKey = ideaKey;
    }

    public boolean requiredFieldsIsNotNull() {
        return token != null && ideaKey != null;
    }
}
