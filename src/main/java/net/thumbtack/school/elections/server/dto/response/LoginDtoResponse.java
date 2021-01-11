package net.thumbtack.school.elections.server.dto.response;

import java.util.UUID;

//dto - пакет с классами, описывающими запросы и ответы. Например,
// если для входа на сервер нужны только логин и пароль из модели User,
// то необходимо создать класс LoginDtoRequest  с двумя полями -
// “login и “password” для запроса и класс LoginDtoResponse с полем “token” для ответа.
public class LoginDtoResponse {
    private String token;

    public LoginDtoResponse(String token) {
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
