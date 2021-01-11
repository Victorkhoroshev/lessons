package net.thumbtack.school.elections.server.dto.response;


import net.thumbtack.school.elections.server.dto.request.Session;

public class LogoutDtoResponse {
    private String token;

    public LogoutDtoResponse(String token) {
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String session) {
        this.token = session;
    }
}
