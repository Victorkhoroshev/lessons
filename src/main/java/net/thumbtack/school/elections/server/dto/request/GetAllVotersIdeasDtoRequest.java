package net.thumbtack.school.elections.server.dto.request;

import java.util.List;

public class GetAllVotersIdeasDtoRequest {
    private String token;
    private List<String> logins;

    public GetAllVotersIdeasDtoRequest(String token, List<String> logins) {
        setToken(token);
        setLogins(logins);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getLogins() {
        return logins;
    }

    public void setLogins(List<String> logins) {
        this.logins = logins;
    }

    public boolean requiredFieldsIsNotNull(){
        return token != null && logins.isEmpty();
    }
}
