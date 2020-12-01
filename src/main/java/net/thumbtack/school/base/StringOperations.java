package net.thumbtack.school.base;

public class StringOperations {
    //1.1
    public static int getSummaryLength(String[] strings) {
        int count = 0;
        for (String str : strings) {
            count += str.length();
        }
        return count;
    }
    //1.2
    public static String getFirstAndLastLetterString(String string) {
        char first = string.charAt(0);
        char last = string.charAt(string.length() - 1);
        return String.valueOf(first) + last;
    }
    //1.3
    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        return string1.charAt(index)
                == string2.charAt(index);
    }
    //1.4
    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        return string1.indexOf(character)
                == string2.indexOf(character);
    }
    //1.5
    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
        return string1.lastIndexOf(character)
                == string2.lastIndexOf(character);
    }
    //1.6
    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        return string1.indexOf(str)
                == string2.indexOf(str);
    }
    //1.7
    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
        return string1.lastIndexOf(str)
                == string2.lastIndexOf(str);
    }
    //1.8
    public static boolean isEqual(String string1, String string2) {
        return string1.equals(string2);
    }
    //1.9
    public static boolean isEqualIgnoreCase(String string1, String string2) {
        return string1.equalsIgnoreCase(string2);
    }
    //1.10
    public static boolean isLess(String string1, String string2) {
        return string1.compareTo(string2) < 0;
    }
    //1.11
    public static boolean isLessIgnoreCase(String string1, String string2) {
        return string1
                .toLowerCase()
                .compareTo(string2.toLowerCase()) < 0;
    }
    //1.12
    public static String concat(String string1, String string2) {
        return string1 + string2;
    }
    //1.13
    public static boolean isSamePrefix(String string1, String string2, String prefix) {
        return string1.startsWith(prefix) &&
                string2.startsWith(prefix);
    }
    //1.14
    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        return string1.endsWith(suffix) &&
                string2.endsWith(suffix);
    }
    //1.15
    public static String getCommonPrefix(String string1, String string2) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars1 = string1.toCharArray();
        char[] chars2 = string2.toCharArray();
        for (int i = 0; i < chars1.length && i < chars2.length; i++) {
            if (chars1[i] == chars2[i]) {
                stringBuilder.append(chars1[i]);
            }
            else {
                return stringBuilder.toString();
            }
        }
        return stringBuilder.toString();
    }
    //1.16
    public static String reverse(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        return stringBuilder
                .reverse()
                .toString();
    }
    //1.17
    public static boolean isPalindrome(String string) {
        return StringOperations
                .reverse(string)
                .equals(string);
    }
    //1.18
    public static boolean isPalindromeIgnoreCase(String string) {
        return StringOperations
                .reverse(string)
                .equalsIgnoreCase(string);
    }
    //1.19
    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        String str = "";
        for (String word : strings) {
           if (StringOperations.reverse(word).equalsIgnoreCase(word) &&
                   str.length() < word.length()) {
               str = word;
           }
       }
        return str;
    }
    //1.20
    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        if (string1.length() < index + length || string2.length() < index + length) {
            return false;
        }
        return string1
                .substring(index, index + length)
                .equals(string2.substring(index, index + length));
    }
    //1.21
    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1,
                                                        String string2, char replaceInStr2, char replaceByInStr2) {
        return string1
                .replace(replaceInStr1, replaceByInStr1)
                .equals(string2.replace(replaceInStr2, replaceByInStr2));
    }
    //1.22
    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1,
                                                     String string2, String replaceInStr2, String replaceByInStr2) {
        return string1
                .replaceAll(replaceInStr1, replaceByInStr1)
                .equals(string2.replace(replaceInStr2, replaceByInStr2));
    }
    //1.23
    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        return StringOperations.isPalindromeIgnoreCase(string.replace(" ", ""));
    }
    //1.24
    public static boolean isEqualAfterTrimming(String string1, String string2) {
        return string1
                .trim()
                .equals(string2.trim());
    }
    //1.25
    public static String makeCsvStringFromInts(int[] array) {
        if (array.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : array) {
            stringBuilder.append(i).append(',');
        }
        return stringBuilder
                .deleteCharAt(stringBuilder.length() - 1)
                .toString();
    }
    //1.26
    public static String makeCsvStringFromDoubles(double[] array) {
        if (array.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (double d : array) {
            stringBuilder.append(String.format("%.2f", d)).append(',');
        }
        return stringBuilder
                .deleteCharAt(stringBuilder.length() - 1)
                .toString();
    }
    //1.27
    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
        return new StringBuilder(StringOperations.makeCsvStringFromInts(array));
    }
    //1.28
    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
        return new StringBuilder(StringOperations.makeCsvStringFromDoubles(array));
    }
    //1.29
    public static StringBuilder removeCharacters(String string, int[] positions) {
        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = positions.length - 1; i >= 0; i--) {
            stringBuilder.deleteCharAt(positions[i]);
        }
        return stringBuilder;
    }
    //1.30
    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        StringBuilder stringBuilder = new StringBuilder(string.length() + positions.length)
                .append(string);
        for (int i = positions.length - 1; i >= 0; i--) {
            if (characters[i] != stringBuilder.charAt(positions[i])) {
                stringBuilder.insert(positions[i], characters[i]);
            }
        }
        return stringBuilder;
    }
}
