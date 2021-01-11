package net.thumbtack.school.elections.server.dto.request;

import net.thumbtack.school.elections.server.model.Voter;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

//dto - пакет с классами, описывающими запросы и ответы. Например,
// если для входа на сервер нужны только логин и пароль из модели User,
// то необходимо создать класс LoginDtoRequest  с двумя полями -
// “login и “password” для запроса и класс LoginDtoResponse с полем “token” для ответа.
public class LoginDtoRequest {
    private String login;
    private String password;

    public LoginDtoRequest(String login, String password) {
        setLogin(login);
        setPassword(password);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean requiredFieldsIsNotNull() {
        return login != null && password != null;
    }

    public boolean isLoginValid() {
        return login.length() > 8;
    }

    public boolean isPasswordValid() {
        Pattern[] patterns = new Pattern[] {Pattern.compile("^(?=.*[a-zа-я]).+$"),
                Pattern.compile("^(?=.*[A-ZА-Я]).+$"),
                Pattern.compile(".*\\d.*"), Pattern.compile(".*\\W.*")};
        for (Pattern pattern: patterns) {
            if (!pattern.matcher(password).matches() || password.length() < 9 || password.matches(".*\\s.*")) {
                return false;
            }
        }
        return true;
    }
}
