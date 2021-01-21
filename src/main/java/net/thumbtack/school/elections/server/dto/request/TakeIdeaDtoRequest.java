package net.thumbtack.school.elections.server.dto.request;

public class TakeIdeaDtoRequest {
    private String token;
    private String ideaToken;

    public TakeIdeaDtoRequest(String token, String ideaToken) {
        setToken(token);
        setIdeaToken(ideaToken);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdeaToken() {
        return ideaToken;
    }

    public void setIdeaToken(String ideaToken) {
        this.ideaToken = ideaToken;
    }

    public boolean requiredFieldsIsNotNull() {
        return token != null && ideaToken != null;
    }
}
