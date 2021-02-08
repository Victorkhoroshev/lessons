package net.thumbtack.school.elections.server.dto.request;

import net.thumbtack.school.elections.server.model.Voter;
import org.jetbrains.annotations.Nullable;
import java.util.regex.Pattern;

public class RegisterDtoRequest {
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

    public RegisterDtoRequest(String firstName, String lastName, @Nullable String patronymic, String street, Integer house,
                              @Nullable Integer apartment, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.login = login;
        this.password = password;
    }

    public boolean isFirstNameValid() {
        return isStringValid(firstName);
    }

    public boolean isLastNameValid() {
        return isStringValid(lastName);
    }

    public boolean isPatronymicValid() {
        return patronymic == null || isStringValid(patronymic);
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
        return apartment== null || apartment >= 0;
    }

    public boolean isLoginValid() {
        return login.length() > 8;
    }

    public boolean isPasswordValid() {
        return passwordValidation(password);
    }

    static boolean passwordValidation(String password) {
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

    public boolean requiredFieldsIsNotNull() {
        return firstName != null && lastName != null && street != null &&
                house != null && login != null && password != null;
    }


    public Voter newVoter() {
        return new Voter(firstName, lastName, patronymic, street, house, apartment, login, password);
    }
}
