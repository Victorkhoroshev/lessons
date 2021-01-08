package net.thumbtack.school.elections.server.model;

import net.thumbtack.school.elections.server.dto.request.RegisterVoterDtoRequest;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

//model - пакет с классами описывающими объекты приложения.
// Например, для  пользователя можно создать класс модели  User.
// Классы модели должны быть максимально простыми,
// содержать поля и методы для получения и переопределения этих полей (геттеры и сеттеры).
// Классы модели не должны содержать методов, выполняющих какие-то действия (запись в файл, в базу данных и т.д.)
// - этим должны заниматься методы класса сервиса с помощью DAO
public class Voter {
    //фамилию, имя и отчество (последнее - если имеется)
    //домашний адрес : улица, дом, квартира (последнее - если имеется)
    //логин и пароль для входа на сервер.
    private final String firstName;
    private final String lastName;
    private final String patronymic;//может не быть
    private final String street;
    private final int house;
    private final int apartment;//может не быть
    private final String login;
    private final String password;
    private String token;

    public Voter (String firstName, String lastName, String patronymic, String street, int house, int apartment, String login, String password) {
        this.firstName = firstName.toLowerCase(Locale.ROOT);
        this.lastName = lastName.toLowerCase(Locale.ROOT);
        this.patronymic = patronymic.toLowerCase(Locale.ROOT);
        this.street = street.toLowerCase(Locale.ROOT);
        this.house = house;
        this.apartment = apartment;
        this.login = login;
        this.password = password;
        setToken();
    }

    public Voter (String firstName, String lastName, String street, int house, int apartment, String login, String password) {
        this(firstName, lastName,"", street, house, apartment, login, password);
    }

    public Voter(RegisterVoterDtoRequest registerVoterDtoRequest) {
        firstName = registerVoterDtoRequest.getFirstName().toLowerCase(Locale.ROOT);
        lastName = registerVoterDtoRequest.getLastName().toLowerCase(Locale.ROOT);
        patronymic = registerVoterDtoRequest.getPatronymic().toLowerCase(Locale.ROOT);
        street = registerVoterDtoRequest.getStreet().toLowerCase(Locale.ROOT);
        house = registerVoterDtoRequest.getHouse();
        apartment = registerVoterDtoRequest.getApartment();
        login = registerVoterDtoRequest.getLogin();
        password = registerVoterDtoRequest.getPassword();
        setToken();
    }

    public Voter (String firstName, String lastName, String patronymic, String street, int house, String login, String password) {
        this(firstName, lastName, patronymic, street, house, 0,  login, password);
    }

    public Voter (String firstName, String lastName, String street, int house, String login, String password) {
        this(firstName, lastName, "", street, house, 0, login, password);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getApartment() {
        return apartment;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken() {
        this.token = UUID.randomUUID().toString();
    }

    public boolean isFirstNameValid() {
        return isStringValid(firstName);
    }

    public boolean isLastNameValid() {
        return isStringValid(lastName);
    }

    public boolean isPatronymicValid() {
        if (patronymic.equals("")) {
            return true;
        }
        return isStringValid(patronymic);
    }

    public boolean isStreetValid() {
        return isStringValid(street);
    }

    private boolean isStringValid(String s) {
        return s.matches("[а-яА-Я]+");
    }

    public boolean isHouseValid() {
        return house > 0;
    }

    public boolean isApartmentValid() {
        return apartment >= 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voter voter = (Voter) o;
        return house == voter.house && apartment == voter.apartment && Objects.equals(firstName, voter.firstName) && Objects.equals(lastName, voter.lastName) && Objects.equals(patronymic, voter.patronymic) && Objects.equals(street, voter.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic, street, house, apartment);
    }
}
