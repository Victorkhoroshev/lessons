package net.thumbtack.school.elections.server.dto.request;

import net.thumbtack.school.elections.server.model.Voter;
import java.util.UUID;

public class RegisterVoterDtoRequest {
    private final String firstName;
    private final String lastName;
    private final String patronymic;//может не быть
    private final String street;
    private final int house;
    private final int apartment;//может не быть
    private final String login;
    private final String password;

    public RegisterVoterDtoRequest (String firstName, String lastName, String patronymic, String street, int house,
                                    int apartment, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.login = login;
        this.password = password;
    }

    public RegisterVoterDtoRequest (Voter voter) {
        firstName = voter.getFirstName();
        lastName = voter.getLastName();
        patronymic = voter.getPatronymic();
        street = voter.getStreet();
        house = voter.getHouse();
        apartment = voter.getApartment();
        login = voter.getLogin();
        password = voter.getPassword();
    }

    public RegisterVoterDtoRequest (String firstName, String lastName, String street,
                                    int house, int apartment, String login, String password) {
        this(firstName, lastName,"", street, house, apartment, login, password);
    }

    public RegisterVoterDtoRequest (String firstName, String lastName, String patronymic,
                                    String street, int house, String login, String password) {
        this(firstName, lastName, patronymic, street, house, 0,  login, password);
    }

    public RegisterVoterDtoRequest (String firstName, String lastName, String street,
                                    int house, String login, String password) {
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

    public boolean isValid() {
        return firstName != null && lastName != null && street != null &&
                house != 0 && login != null && password != null;
//        return
//                && login.length() > 8 && password.length() > 8 && password.matches("A - Z") &&
//                password.matches("a - z") && password.matches("/d") &&
    }


}
