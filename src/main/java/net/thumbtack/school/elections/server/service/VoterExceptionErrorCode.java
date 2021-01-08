package net.thumbtack.school.elections.server.service;

public enum VoterExceptionErrorCode {
    VOTER_ALREADY_EXISTS("Вы уже зарегестрированны"),
    VOTER_NOT_FOUND("Такой пользователь не зарегистрирован"),
    VOTER_LOGIN_ALREADY_EXISTS("Такой логин уже используется"),
    VOTER_LOGIN_INVALID("Длинна логина должна быть не меньше 9 символов"),
    VOTER_PASSWORD_INVALID("Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву," +
            " цифру и один спец. символ, а его длинна не менее 9 символов, без пробелов"),
    VOTER_FIRSTNAME_INVALID("Имя должно быть на кириллице, без пробелов, спец. символов и цифр"),
    VOTER_LASTNAME_INVALID("Фамилия должна быть на кириллице, без пробелов, спец. символов и цифр"),
    VOTER_PATRONYMIC_INVALID("Отчество должно быть на кириллице, без пробелов, спец. символов и цифр"),
    VOTER_STREET_INVALID("Название улицы должно быть на кириллице, без пробелов, спец. символов и цифр"),
    VOTER_HOUSE_INVALID("Номер дома не может быть меньше единицы"),
    VOTER_APARTMENT_INVALID("Номер квартиры не может быть меньше нуля");

    private final String message;

    VoterExceptionErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
