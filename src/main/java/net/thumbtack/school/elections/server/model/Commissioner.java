package net.thumbtack.school.elections.server.model;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class Commissioner implements Serializable {
    private final String firstName;
    private final String lastName;
    private final String login;
    private final String password;
    private final boolean isChairman;

    public Commissioner(String firstName, String lastName, String login, String password, boolean isChairman) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.isChairman = isChairman;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isChairman() {
        return isChairman;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commissioner that = (Commissioner) o;
        return isChairman == that.isChairman && firstName.equals(that.firstName) && lastName.equals(that.lastName) && login.equals(that.login) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, login, password, isChairman);
    }
}
