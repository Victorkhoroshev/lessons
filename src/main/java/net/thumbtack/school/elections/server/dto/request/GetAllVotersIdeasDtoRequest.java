package net.thumbtack.school.elections.server.dto.request;

import java.util.List;

public class GetAllVotersIdeasDtoRequest {
    private String token;
    private List<String> voterLogins;

    public GetAllVotersIdeasDtoRequest(String token, List<String> voterLogins) {
        setToken(token);
        setVoterLogins(voterLogins);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getVoterLogins() {
        return voterLogins;
    }

    public void setVoterLogins(List<String> voterLogins) {
        this.voterLogins = voterLogins;
    }

    public boolean requiredFieldsIsNotNull(){
        return token != null && voterLogins.isEmpty();
    }
}
