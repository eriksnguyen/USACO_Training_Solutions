/*
 ID: eriksng1
 LANG: JAVA
 TASK: checker
 */

import java.io.*;

public class checker {

    /*
     * Finds all solutions to the N Queens problems and prints out the first
     * 3 found.
     * 
     * @Param: 
     * N: determines the number of queens and the size of the board.
     * 
     * offset: helps to determine which diagonal a queen at location (r, c) is
     * in. The diagonals are marked as follows for an N = 8 board
     * 
     * d1: diagonal from left to right (extend the numbers diagonally to
     * see which diagonals they correspond to)
     * 
     *  7 6 5 4 3 2 1 0
     *  8|#|_|#|_|#|_|#|_|
     *  9|_|#|_|#|_|#|_|#|
     * 10|#|_|#|_|#|_|#|_|
     * 11|_|#|_|#|_|#|_|#|
     * 12|#|_|#|_|#|_|#|_|
     * 13|_|#|_|#|_|#|_|#|
     * 14|#|_|#|_|#|_|#|_|
     *   |_|#|_|#|_|#|_|#|
     * 
     * d2: diagonal from right to left (similar to d1)
     *    0 1 2 3 4 5 6 7
     * |#|_|#|_|#|_|#|_|8
     * |_|#|_|#|_|#|_|#|9
     * |#|_|#|_|#|_|#|_|10
     * |_|#|_|#|_|#|_|#|11
     * |#|_|#|_|#|_|#|_|12
     * |_|#|_|#|_|#|_|#|13
     * |#|_|#|_|#|_|#|_|14
     * |_|#|_|#|_|#|_|#|
     * 
     * from the two above. Assuming that 0 <= r, c < N marks a location in the
     * grid, diagonal 1 is defined as (r - c + (N - 1)) and diagonal 2 is
     * defined as (r + c). this leads to offset being defined as (N - 1).
     * 
     * columns: marks which columns are filled at any given time.
     * 
     * rows: marks which columns are put in which rows and dynamically changes
     * 
     * rowBuffer: is only updated when a column is set into a row for testing
     *      allowing it to serve as a backtrack point for the dynamically
     *      changing rows array.
     * 
     * count: counts the number of solutions found
     */
    
    private static PrintWriter p;
    private static int[] rows, rowBuffer;
    private static boolean[] d1, d2, columns;
    private static int count;
    private static int N;
    private static int offset;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("checker.in"));
        N = Integer.parseInt(in.readLine().trim());

        init();

        p = new PrintWriter(new File("checker.out"));
        solve();
        p.println(count);
        p.close();
        System.exit(0);
    }

    private static void init() {
        rows = new int[N];
        rowBuffer = new int[N];
        d1 = new boolean[2 * N - 1];
        d2 = new boolean[2 * N - 1];
        columns = new boolean[N];
        offset = N - 1;

        for (int i = 0; i < N; i++) {
            rows[i] = rowBuffer[i] = -1;
        }
    }

    /*
     * Checks for collision of queens.
     */
    private static boolean unsafe(int row) {
        return  columns[rows[row]] || //value in column exists
                d1[calDiag1(row, rows[row])] || //value in diag1 exists
                d2[calDiag2(row, rows[row])]; //value in diag2 exists
    }

    private static void solve() {
        int row = 0;
        while (row > - 1) {
            do {
                int col = rows[row];
                if (col != -1 && rowBuffer[row] == col) {//resets in backtrack
                    rowBuffer[row] = -1;
                    columns[rows[row]] = false;
                    d1[calDiag1(row, rows[row])] = false;
                    d2[calDiag2(row, rows[row])] = false;
                }
                rows[row]++;//increase column value set at row
            } while ((rows[row] < N) && unsafe(row));
            if (rows[row] < N) {
                //mark save
                rowBuffer[row] = rows[row];
                columns[rows[row]] = true;
                d1[calDiag1(row, rows[row])] = true;
                d2[calDiag2(row, rows[row])] = true;
                if (row < N - 1) {
                    ++row;
                } else if (++count < 4) {
                    print();
                }
            } else {//backtrack
                rows[row] = -1;
                row--;
            }
        }
    }

    private static void print() {
        StringBuilder out = new StringBuilder();
        for (int i : rows) {
            out.append(i + 1).append(" ");
        }
        p.println(out.toString().trim());
    }

    private static int calDiag1(int row, int col) {
        return row - col + offset;
    }

    private static int calDiag2(int row, int col) {
        return row + col;
    }
}
