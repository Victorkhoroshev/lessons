package net.thumbtack.school.matrix;

import java.util.*;

public class MatrixNonSimilarRows {
    int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {
        Set<int[]> result = new HashSet<>();
        Set<Integer> valuesNext = new TreeSet<>();
        List<Set<Integer>> values = new ArrayList<>();
        for (int[] line : matrix) {
            for (int value : line) {
                valuesNext.add(value);
            }
            if (values.stream().noneMatch(value -> value.equals(valuesNext)) || result.isEmpty()) {
                result.add(line);
            }
            values.add(new TreeSet<>(valuesNext));
            valuesNext.clear();
        }
        return result;
    }
}
