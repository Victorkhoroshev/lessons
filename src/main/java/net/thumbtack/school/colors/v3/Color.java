package net.thumbtack.school.colors.v3;

public enum  Color {
    RED, GREEN, BLUE;

    public static Color colorFromString(String colorString) throws ColorException {
        if (colorString == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
        if (colorString.length() == 0) {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        }
        try {
            return Color.valueOf(colorString);
        } catch (IllegalArgumentException e) {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        }
    }
}
