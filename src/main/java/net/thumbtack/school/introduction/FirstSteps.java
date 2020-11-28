package net.thumbtack.school.introduction;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstSteps {
    //1
    public int sum(int x, int y) {
        return x + y;
    }
    //2
    public int mul(int x, int y) {
        return x * y;
    }
    //3
    public int div(int x, int y) {
        return x / y;
    }
    //4
    public int mod(int x, int y) {
        return x % y;
    }
    //5
    public boolean isEqual(int x, int y) {
        boolean flag = false;
        if (x == y)
            flag = true;
        return flag;
    }
    //6
    public boolean isGreater(int x, int y) {
        boolean flag = false;
        if (x > y)
            flag = true;
        return flag;
    }
    //7
    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        boolean flag = false;
        if(x >= xLeft && x <= xRight && y >= yTop && y <= yBottom)
            flag = true;
        return flag;
    }
    //8
    public int sum(int[] array) {
        int sum = 0;
        for (int i : array)
            sum = sum + i;
        return sum;
    }
    //9
    public int mul(int[] array) {
        if (array.length != 0) {
            int mul = 1;
            for (int i : array)
                mul = mul * i;
            return mul;
        }
        return 0;
    }
    //10
    public int min(int[] array) {
        if (array.length != 0) {
            Arrays.sort(array);
            return array[0];
        }
        return Integer.MAX_VALUE;
    }
    //11
    public int max(int[] array) {
        if(array.length != 0) {
            Arrays.sort(array);
            return array[array.length - 1];
        }
        return Integer.MIN_VALUE;
    }
    //12
    public double average(int[] array) {
        if(array.length != 0) {
            int count = 0;
            int summ = 0;
            double average;
            for (int i : array) {
                summ = summ + i;
                count++;
            }
            average = (double) summ / count;
            return average;
        }
        return 0;
    }
    //13
    public boolean isSortedDescendant(int[] array) {
        boolean flag = true;
        if (array.length != 0) {
            int number = array[0];
            int count = 1;
            for (int i : array) {
                if (i < number) {
                    count++;
                    number = i;
                }
            }
            if (count != array.length)
                flag = false;
        }
        return flag;
    }
    //14
    public void cube(int[] array) {
        for(int i = 0; i < array.length; i++) {
            array[i] = (int) Math.pow(array[i], 3);
        }
    }
    //15
    public boolean find(int[] array, int value) {
        for(int i : array) {
            if (i == value)
                return true;
        }
        return false;
    }
    //16
    public void reverse(int[] array) {
        int[] arrayReverse = new int[array.length];
        int count = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            arrayReverse[count] = array[i];
            count++;
        }
        for(int i = 0; i < arrayReverse.length; i++) {
            array[i] = arrayReverse[i];
        }
    }
    //17
    public boolean isPalindrome(int[] array) {
        int b = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != array[b])
                return false;
            b--;
        }
        return true;
    }
    //18
    public int sum(int[][] matrix) {
        int sum = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int b = 0; b < matrix[i].length; b++){
                sum = sum + matrix[i][b];
            }
        }
        return sum;
    }
    //19
    public int max(int[][] matrix) {
        if(matrix[0].length != 0) {
            int max = matrix[0][0];
            for (int i = 0; i < matrix.length; i++) {
                for (int b = 0; b < matrix[i].length; b++) {
                    if (matrix[i][b] > max)
                        max = matrix[i][b];
                }
            }
            return max;
        }
        return Integer.MIN_VALUE;
    }
    //20
    public int diagonalMax(int[][] matrix) {
        if(matrix[0].length != 0) {
            int max = matrix[0][0];
            for(int i = 0; i < matrix.length; i++) {
                for(int b = 0; b < matrix[i].length; b++) {
                    if (matrix[i][i] > max)
                        max = matrix[i][i];
                }
            }
            return max;
        }
        return Integer.MIN_VALUE;
    }
    //21
    public boolean isSortedDescendant(int[][] matrix) {
        boolean flag = true;
        if (matrix.length != 0) {
            FirstSteps firstSteps = new FirstSteps();

            for (int i = 0; i < matrix.length; i++) {
                int[] array = new int[matrix[i].length];
                for (int b = 0; b < matrix[i].length; b++) {
                    array[b] = matrix[i][b];
                }
                flag = firstSteps.isSortedDescendant(array);
                if (!flag)
                    return flag;
            }
        }
        return flag;
    }
}
