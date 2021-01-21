package net.thumbtack.school.elections.server.dto.request;

public class ChangeRatingDtoRequest {
    private String token;
    private String ideasToken;
    private int rating;

    public ChangeRatingDtoRequest(String token, String ideasToken, int rating) {
        setToken(token);
        setIdeasToken(ideasToken);
        setRating(rating);
    }

    public String getIdeasToken() {
        return ideasToken;
    }

    public void setIdeasToken(String ideasToken) {
        this.ideasToken = ideasToken;
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
        return token != null && ideasToken != null;
    }
}
