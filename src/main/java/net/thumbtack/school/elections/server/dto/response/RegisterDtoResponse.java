package net.thumbtack.school.elections.server.dto.response;

public class RegisterDtoResponse {

    private String token;

    public RegisterDtoResponse(String token) {
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
