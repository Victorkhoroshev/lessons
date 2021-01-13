package net.thumbtack.school.elections.server.model;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

//model - пакет с классами описывающими объекты приложения.
// Например, для  пользователя можно создать класс модели  User.
// Классы модели должны быть максимально простыми,
// содержать поля и методы для получения и переопределения этих полей (геттеры и сеттеры).
// Классы модели не должны содержать методов, выполняющих какие-то действия (запись в файл, в базу данных и т.д.)
// - этим должны заниматься методы класса сервиса с помощью DAO
public class Voter implements Serializable {
    //фамилию, имя и отчество (последнее - если имеется)
    //домашний адрес : улица, дом, квартира (последнее - если имеется)
    //логин и пароль для входа на сервер.
    private final String firstName;
    private final String lastName;
    @Nullable
    private final String patronymic;
    private final String street;
    private final Integer house;
    @Nullable
    private final Integer apartment;
    private final String login;
    private final String password;

    public Voter (String firstName, String lastName, @Nullable String patronymic, String street, Integer house, @Nullable Integer apartment, String login, String password) {
        this.firstName = firstName.toLowerCase(Locale.ROOT);
        this.lastName = lastName.toLowerCase(Locale.ROOT);
        if( patronymic != null) {
            this.patronymic = patronymic.toLowerCase(Locale.ROOT);
        } else {
            this.patronymic = null;
        }
        this.street = street.toLowerCase(Locale.ROOT);
        this.house = house;
        this.apartment = apartment;
        this.login = login;
        this.password = password;
    }

    public Voter (String firstName, String lastName, String street, Integer house, Integer apartment, String login, String password) {
        this(firstName, lastName,null, street, house, apartment, login, password);
    }

    public Voter (String firstName, String lastName, String patronymic, String street, Integer house, String login, String password) {
        this(firstName, lastName, patronymic, street, house, null,  login, password);
    }

    public Voter (String firstName, String lastName, String street, Integer house, String login, String password) {
        this(firstName, lastName, null, street, house, null, login, password);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Nullable
    public String getPatronymic() {
        return patronymic;
    }

    public String getStreet() {
        return street;
    }

    public Integer getHouse() {
        return house;
    }

    @Nullable
    public Integer getApartment() {
        return apartment;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voter voter = (Voter) o;
        return Objects.equals(firstName, voter.firstName) && Objects.equals(lastName, voter.lastName) && Objects.equals(patronymic, voter.patronymic) && Objects.equals(street, voter.street) && Objects.equals(house, voter.house) && Objects.equals(apartment, voter.apartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, patronymic, street, house, apartment);
    }
}
