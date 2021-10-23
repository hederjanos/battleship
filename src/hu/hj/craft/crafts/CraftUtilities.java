package hu.hj.craft.crafts;

import hu.hj.constants.Orientation;

public class CraftUtilities {

    private CraftUtilities() {
    }

    public static int[][] getOrientedShape(int[][] shape, Orientation orientation) {
        int[][] orientedShape = deepCopyShape(shape);
        for (int i = 0; i < orientation.ordinal(); i++) {
            orientedShape = rotateShapeRight(orientedShape);
        }
        return orientedShape;
    }

    protected static int[][] deepCopyShape(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            int[] row = original[i];
            int col = row.length;
            copy[i] = new int[col];
            System.arraycopy(row, 0, copy[i], 0, col);
        }
        return copy;
    }

    protected static int[][] rotateShapeRight(int[][] shape) {
        int[][] orientedShape = new int[shape.length][shape.length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape.length; j++) {
                orientedShape[i][j] = shape[shape.length - 1 - j][i];
            }
        }
        return orientedShape;
    }

    public static int[] searchPositionInShape(int[][] shape, int value) {
        int[] searchedPosition = new int[2];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape.length; j++) {
                if (shape[i][j] == value) {
                    searchedPosition[0] = j;
                    searchedPosition[1] = i;
                }
            }
        }
        return searchedPosition;
    }
}