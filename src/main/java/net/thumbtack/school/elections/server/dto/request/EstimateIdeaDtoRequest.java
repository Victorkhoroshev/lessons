package net.thumbtack.school.elections.server.dto.request;

public class EstimateIdeaDtoRequest {
    private String ideasToken;
    private int rating;
    private String token;

    public EstimateIdeaDtoRequest(String ideasToken, int rating, String token) {
        setIdeasToken(ideasToken);
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

    public String getIdeasToken() {
        return ideasToken;
    }

    public void setIdeasToken(String ideasToken) {
        this.ideasToken = ideasToken;
    }

    public boolean requiredFieldsIsNotNull() {
        return token != null;
    }
}
