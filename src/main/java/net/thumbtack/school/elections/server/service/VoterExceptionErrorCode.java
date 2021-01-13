package net.thumbtack.school.elections.server.service;

public enum VoterExceptionErrorCode {
    VOTER_ALREADY_EXISTS("Вы уже зарегестрированны."),
    VOTER_LOGOUT("Сессия пользователя не найдена."),
    VOTER_NOT_FOUND("Пользователь не найден."),
    VOTER_LOGIN_ALREADY_EXISTS("Такой логин уже используется."),
    VOTER_WRONG_PASSWORD("Неверный пароль.");

    private final String message;

    VoterExceptionErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
