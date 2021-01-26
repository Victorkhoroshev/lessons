package net.thumbtack.school.elections.server.dto.request;

public class EstimateIdeaDtoRequest {
    private String ideaKey;
    private int rating;
    private String token;

    public EstimateIdeaDtoRequest(String ideaKey, int rating, String token) {
        setIdeaKey(ideaKey);
        setRating(rating);
        setToken(token);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
        return token != null;
    }
}
