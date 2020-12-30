package net.thumbtack.school.matrix;

import java.util.*;

public class MatrixNonSimilarRows {
    private final int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {
        Set<Integer> values = new HashSet<>();
        Map<Set<Integer>, int[]> map = new HashMap<>();
        for (int[] line : matrix) {
            for (int value : line) {
                values.add(value);
            }
            map.putIfAbsent(new HashSet<>(values), line);
            values.clear();
        }
        return new HashSet<>(map.values());
    }
}
