import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class trzynaste {

    public static void main(String[] args) {

        File f = new File("kraby.txt");

        ArrayList<Integer> crabs = new ArrayList<Integer>();

        try (Scanner s = new Scanner(f)) {

            s.useDelimiter(";|\\r?\\n|\\r");
            String[] tab = s.next().split("[,]");

            int maxValue = 0;
            int minValue = 99999;

            for(String st: tab){
                crabs.add(Integer.parseInt(st));

                if(Integer.parseInt(st) > maxValue)
                    maxValue = Integer.parseInt(st);

                if(Integer.parseInt(st) < minValue)
                    minValue = Integer.parseInt(st);
            }

            int minFuelCost = 999999999; // najmniejszy koszt
            int currentFuelCost = 0; // koszt dojscia do aktualnie rozpatrywanej pozycji

            // rozpatruje kolejne pozycje
            for (int i = 0; i< maxValue+minValue; i++){
                currentFuelCost = 0;

                // sumuje koszty dojscia do pozycji i dla kazdego z krabow
                for(int c : crabs){
                    currentFuelCost += Math.abs(c - i);

                    if(currentFuelCost > minFuelCost) // jesli aktualny koszt jest wiekszy niz dotychczasowe minimum to nie ma sensu dalej szukac
                        break;
                }

                // czy aktualny jest mniejszy niz najmniejszy do tej pory znaleziony
                // jesli tak, to mamy nowy najmniejszy koszt
                if (currentFuelCost < minFuelCost)
                    minFuelCost = currentFuelCost;
            }

            System.out.println(minFuelCost);
        }



        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Blad e");
        }

    }

}
