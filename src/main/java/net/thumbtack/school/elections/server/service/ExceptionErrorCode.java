package net.thumbtack.school.elections.server.service;

public enum ExceptionErrorCode {
    ALREADY_EXISTS("Вы уже зарегестрированны."),
    LOGOUT("Сессия пользователя не найдена."),
    RATING_INCORRECT("Оценка должна быть от 1 до 5."),
    NOT_FOUND("Пользователь не найден."),
    LOGIN_ALREADY_EXISTS("Такой логин уже используется."),
    WRONG_PASSWORD("Неверный пароль."),
    NOT_CHAIRMAN("Вы не председатель коммиссии."),
    CANDIDATE_NOT_FOUND("Кандидат не найден."),
    IDEA_NOT_FOUND("Идея не найдена."),
    ELECTION_START("Выборы уже проходят, действие невозможно."),
    ELECTION_NOT_START("Голосование не началось."),
    ELECTION_STOP("Голосование закончилось.");

    private final String message;

    ExceptionErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
