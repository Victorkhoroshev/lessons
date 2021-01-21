package net.thumbtack.school.elections.server.service;

public enum ExceptionErrorCode {
    VOTER_ALREADY_EXISTS("Вы уже зарегестрированны."),
    VOTER_LOGOUT("Сессия пользователя не найдена."),
    RATING_INCORRECT("Оценка должна быть от 1 до 5."),
    VOTER_NOT_FOUND("Пользователь не найден."),
    VOTER_LOGIN_ALREADY_EXISTS("Такой логин уже используется."),
    VOTER_WRONG_PASSWORD("Неверный пароль."),
    CANDIDATE_NOT_WITHDRAW_CANDIDACY("Невозможно разлогиниться, для начала, снимите свою кандидатуру с выборов."),
    CANDIDATE_NOT_FOUND("Кандидат не найден");

    private final String message;

    ExceptionErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
