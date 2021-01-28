package net.thumbtack.school.elections.server.dto.request;

public class GetVoterListDtoRequest {
    private String token;

    public GetVoterListDtoRequest(String token) {
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean requiredFieldsIsNotNull() {
        return token != null;
    }
}
