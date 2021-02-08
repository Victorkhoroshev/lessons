package net.thumbtack.school.elections.server.model;

import java.io.Serializable;
import java.util.Objects;

public class Commissioner implements Serializable {
    private final String login;
    private final String password;
    private final boolean isChairman;

    public Commissioner(String login, String password, boolean isChairman) {
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
        return login.equals(that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
