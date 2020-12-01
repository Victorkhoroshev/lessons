package net.thumbtack.school.base;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberOperations {

    //2.1
    public static Integer find(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return null;
    }
    //2.2
    public static Integer find(double[] array, double value, double eps) {
        for (int i = 0; i < array.length; i++) {
            if (Math.abs(array[i]) >= Math.abs(value) - eps &&
                    Math.abs(array[i]) <= Math.abs(value) + eps) {
                return i;
            }
        }
        return null;
    }
    //2.3
    public static Double calculateDensity(double weight, double volume, double min, double max) {
        double result = weight / volume;
        if (result <= max && result >= min) {
            return result;
        }
        return null;
    }
    //2.4
    public static Integer find(BigInteger[] array, BigInteger value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return null;
    }
    //2.5
    public static BigDecimal calculateDensity(BigDecimal weight, BigDecimal volume, BigDecimal min, BigDecimal max) {
        BigDecimal result = weight.divide(volume);
        if (result.compareTo(max) < 0 && result.compareTo(min) > 0) {
            return result;
        }
        return null;
    }


}
