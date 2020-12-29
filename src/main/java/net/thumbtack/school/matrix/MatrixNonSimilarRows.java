package net.thumbtack.school.matrix;

import java.util.*;

public class MatrixNonSimilarRows {
    //REVU: private modifier?
    int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {
        Set<int[]> result = new HashSet<>();
        Set<Integer> valuesNext = new TreeSet<>();
        //REVU: заведите Map<Set<Integer>, int[]> map
        List<Set<Integer>> values = new ArrayList<>();
        for (int[] line : matrix) {
            for (int value : line) {
                valuesNext.add(value);
            }
            //REVU: тут будете ложить данные в Map
            if (values.stream().noneMatch(value -> value.equals(valuesNext)) || result.isEmpty()) {
                result.add(line);
            }
            values.add(new TreeSet<>(valuesNext));
            valuesNext.clear();
        }
        //REVU: а тут возвращать значения из Map
        return result;
    }
}
