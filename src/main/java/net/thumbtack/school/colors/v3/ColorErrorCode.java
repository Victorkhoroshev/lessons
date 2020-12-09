package net.thumbtack.school.colors.v3;

public enum ColorErrorCode {
    WRONG_COLOR_STRING("Wrong color"),
    NULL_COLOR("Color can not be null");

    private String errorString;

    ColorErrorCode(String string) {
        errorString = string;
    }

    public String getErrorString() {
        return errorString;
    }
}
