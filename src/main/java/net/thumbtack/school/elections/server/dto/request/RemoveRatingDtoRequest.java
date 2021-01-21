package net.thumbtack.school.elections.server.dto.request;

public class RemoveRatingDtoRequest {
    private String token;
    private String ideaToken;

    public RemoveRatingDtoRequest(String token, String ideaToken) {
        setToken(token);
        setIdeaToken(ideaToken);
    }

    public String getIdeaToken() {
        return ideaToken;
    }

    public void setIdeaToken(String ideaToken) {
        this.ideaToken = ideaToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean requiredFieldsIsNotNull() {
        return token != null && ideaToken != null;
    }
}
