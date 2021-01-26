package net.thumbtack.school.elections.server.dto.request;

public class RemoveRatingDtoRequest {
    private String token;
    private String ideaKey;

    public RemoveRatingDtoRequest(String token, String ideaKey) {
        setToken(token);
        setIdeaKey(ideaKey);
    }

    public String getIdeaKey() {
        return ideaKey;
    }

    public void setIdeaKey(String ideaKey) {
        this.ideaKey = ideaKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean requiredFieldsIsNotNull() {
        return token != null && ideaKey != null;
    }
}
