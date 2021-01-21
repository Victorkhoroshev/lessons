package net.thumbtack.school.elections.server.dto.request;

public class AddIdeaDtoRequest {
    private String idea;
    private String token;

    public AddIdeaDtoRequest(String idea, String token) {
        setIdea(idea);
        setToken(token);
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean requiredFieldsIsNotNull() {
        return idea != null && !idea.equals("") && token != null;
    }
}
