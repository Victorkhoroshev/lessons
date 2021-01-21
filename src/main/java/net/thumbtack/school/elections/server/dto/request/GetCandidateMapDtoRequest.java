package net.thumbtack.school.elections.server.dto.request;

public class GetCandidateMapDtoRequest {
    private String token;

    public GetCandidateMapDtoRequest(String token) {
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean requiredFieldsIsNotNull(){
        return token != null;
    }
}
