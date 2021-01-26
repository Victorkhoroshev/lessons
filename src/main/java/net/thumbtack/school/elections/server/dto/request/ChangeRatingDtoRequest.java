package net.thumbtack.school.elections.server.dto.request;

public class ChangeRatingDtoRequest {
    private String token;
    private String ideaKey;
    private int rating;

    public ChangeRatingDtoRequest(String token, String ideaKey, int rating) {
        setToken(token);
        setIdeaKey(ideaKey);
        setRating(rating);
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean requiredFieldsIsNotNull() {
        return token != null && ideaKey != null;
    }
}
