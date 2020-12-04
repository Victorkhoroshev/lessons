package net.thumbtack.school.introduction;

import java.util.Arrays;

public class FirstSteps {

    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        return x == y;
    }

    public boolean isGreater(int x, int y) {
        return x > y;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        return x >= xLeft &&
                x <= xRight &&
                y >= yTop &&
                y <= yBottom;
    }

    public int sum(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum = sum + i;
        }
        return sum;
    }

    public int mul(int[] array) {
        if (array.length != 0) {
            int mul = 1;
            for (int i : array) {
                mul = mul * i;
            }
            return mul;
        }
        return 0;
    }

    public int min(int[] array) {
        if (array.length != 0) {
            Arrays.sort(array);
            return array[0];
        }
        return Integer.MAX_VALUE;
    }

    public int max(int[] array) {
        if(array.length != 0) {
            Arrays.sort(array);
            return array[array.length - 1];
        }
        return Integer.MIN_VALUE;
    }

    public double average(int[] array) {
        int count = 0;
        int summ = 0;
        double average = 0;

        if(array.length != 0) {
            for (int i : array) {
                summ = summ + i;
                count++;
            }
            average = (double) summ / count;
        }
        return average;
    }

    public boolean isSortedDescendant(int[] array) {
        if (array.length != 0) {
           for (int i = 0; i < array.length; i++) {
               for (int j = i + 1; j < array.length; j++) {
                   if (array[i] <= array[j]) {
                       return false;
                   }
               }
           }
        }
        return true;
    }

    public void cube(int[] array) {
        for(int i = 0; i < array.length; i++) {
            array[i] = (int) Math.pow(array[i], 3);
        }
    }

    public boolean find(int[] array, int value) {
        for(int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    public void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }
    }

    public boolean isPalindrome(int[] array) {
        int b = array.length - 1;

        for (int i = 0; i < array.length; i++, b--) {
            if (array[i] != array[b]) {
                return false;
            }
        }
        return true;
    }

    public int sum(int[][] matrix) {
        int sum = 0;

        for (int[] ints : matrix) {
            for (int anInt : ints) {
                sum = sum + anInt;
            }
        }
        return sum;
    }

    public int max(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        int interimMax;

        for (int[] ints : matrix) {
            if ((interimMax = max(ints)) > max) {
                max = interimMax;
            }
        }
        return max;
    }

    public int diagonalMax(int[][] matrix) {
        int max = Integer.MIN_VALUE;

        try {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][i] > max) {
                    max = matrix[i][i];
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.getMessage();
        }
        return max;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int[] array = new int[matrix[i].length];
            for (int b = 0; b < matrix[i].length; b++) {
                array[b] = matrix[i][b];
            }
            if (!isSortedDescendant(array)) {
                return false;
            }
        }
        return true;
    }
}
