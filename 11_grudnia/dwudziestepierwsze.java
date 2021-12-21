import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class dwudziestepierwsze {
    private static Integer flashNumber = 0;

    public static void main(String[] args) {

        File f = new File("osmiornice.txt");

        ArrayList<String> rowList = new ArrayList<String>();

        try (Scanner s = new Scanner(f)) {

            s.useDelimiter(";|\\r?\\n|\\r");

            while (s.hasNext()) {
                rowList.add(s.next());
            }

            int[][] tab = new int[rowList.size()][rowList.get(0).length()];
            boolean[][] flashes = new boolean[rowList.size()][rowList.get(0).length()]; // zapamietuje wybuchy w danym kroku

            // przypisuje poczatkowe wartosci osmiornicom
            for (int i = 0; i < rowList.size(); i++) {

                String current = rowList.get(i);

                for (int j = 0; j < current.length(); j++)
                    tab[i][j] = Integer.parseInt(current.substring(j, j + 1));
            }

            int steps = 100;
            int columnSize = rowList.size();
            int rowSize = rowList.get(0).length();


            for (int step = 1; step <= steps; step++) { // poszczegolne kroki

                // zwiekszam energie o 1
                for (int i = 0; i < columnSize; i++) {

                    for (int j = 0; j < rowSize; j++) {

                        flashes[i][j] = false;
                        tab[i][j]++;
                    }
                }

                // sprawdzam, czy ktoras jest wieksza od 9
                for (int i = 0; i < columnSize; i++) {

                    for (int j = 0; j < rowSize; j++) {

                        if (tab[i][j] > 9)
                            doFlash(columnSize, rowSize, tab, i, j, flashes);
                    }
                }
            }

            System.out.println(flashNumber);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Blad e");
        }
    }

    private static void doFlash(int rows, int columns, int[][] tab, int i, int j, boolean[][] flashes) {

        // jesli w danym kroku wybuch nie nastapil
        if (!flashes[i][j]) {

            flashes[i][j] = true;
            tab[i][j] = 0;
            flashNumber++;

            if (j != columns - 1) {

                if (!flashes[i][j + 1]) {

                    tab[i][j + 1]++;

                    if (tab[i][j + 1] > 9) {
                        doFlash(rows, columns, tab, i, j + 1, flashes);
                    }
                }


                if (i != 0) {

                    if (!flashes[i - 1][j + 1]) {

                        tab[i - 1][j + 1]++;

                        if (tab[i - 1][j + 1] > 9) {
                            doFlash(rows, columns, tab, i - 1, j + 1, flashes);
                        }
                    }
                }


                if (i != rows - 1) {

                    if (!flashes[i + 1][j + 1]) {

                        tab[i + 1][j + 1]++;

                        if (tab[i + 1][j + 1] == 10) {
                            doFlash(rows, columns, tab, i + 1, j + 1, flashes);
                        }
                    }

                }

            }

            if (j != 0) {

                if (!flashes[i][j - 1]) {

                    tab[i][j - 1]++;

                    if (tab[i][j - 1] > 9) {
                        doFlash(rows, columns, tab, i, j - 1, flashes);
                    }
                }


                if (i != 0) {

                    if (!flashes[i - 1][j - 1]) {

                        tab[i - 1][j - 1]++;

                        if (tab[i - 1][j - 1] > 9) {
                            doFlash(rows, columns, tab, i - 1, j - 1, flashes);
                        }
                    }
                }


                if (i != rows - 1) {

                    if (!flashes[i + 1][j - 1]) {

                        tab[i + 1][j - 1]++;

                        if (tab[i + 1][j - 1] > 9) {
                            doFlash(rows, columns, tab, i + 1, j - 1, flashes);
                        }
                    }

                }

            }

            if (i != 0) {

                if (!flashes[i - 1][j]) {

                    tab[i - 1][j]++;

                    if (tab[i - 1][j] > 9) {
                        doFlash(rows, columns, tab, i - 1, j, flashes);
                    }
                }

            }


            if (i != rows - 1) {

                if (!flashes[i + 1][j]) {

                    tab[i + 1][j]++;

                    if (tab[i + 1][j] > 9) {
                        doFlash(rows, columns, tab, i + 1, j, flashes);
                    }
                }

            }

            tab[i][j] = 0;
        }
    }
}
