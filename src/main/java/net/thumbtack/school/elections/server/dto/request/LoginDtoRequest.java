package net.thumbtack.school.elections.server.dto.request;
//dto - пакет с классами, описывающими запросы и ответы. Например,
// если для входа на сервер нужны только логин и пароль из модели User,
// то необходимо создать класс LoginDtoRequest  с двумя полями -
// “login и “password” для запроса и класс LoginDtoResponse с полем “token” для ответа.
public class LoginDtoRequest {
    private String login;
    private String password;
}
