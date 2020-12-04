package net.thumbtack.school.introduction;
//REVU: удаляйте неиспользуйте импорты
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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

        //REVU: переиспользуйте метод max для линейного массива
        for(int[] ints : matrix) {
            for(int anInt : ints) {
                if (anInt > max) {
                    max = anInt;
                }
            }
        }
        return max;
    }

    public int diagonalMax(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            //REVU: зачем нам 2ой цикл?
            for (int n : matrix[i]) {
                if (matrix[i][i] > max) {
                    max = matrix[i][i];
                }
            }
        }
        return max;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        //REVU: методы классы доступны внутри и без создания объекта. Это лишнее
        FirstSteps firstSteps = new FirstSteps();

        for (int i = 0; i < matrix.length; i++) {
            int[] array = new int[matrix[i].length];
            for (int b = 0; b < matrix[i].length; b++) {
                array[b] = matrix[i][b];
            }
            if (!firstSteps.isSortedDescendant(array)) {
                return false;
            }
        }
        return true;
    }
}
