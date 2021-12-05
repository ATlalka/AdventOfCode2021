import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class pierwsze {

	public static void main(String[] args) {

		ArrayList<Integer> measurement = new ArrayList<Integer>();

		try (Scanner s = new Scanner(new File("pomiary.txt"))) {

			s.useDelimiter(";|\\r?\\n|\\r");

			while (s.hasNext()) { // wczytujemy wszystkie pomiary

				measurement.add(s.nextInt()); // i dodajemy je do listy
			}
		}

		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Blad e");
		}

		int counter = 0;

		for (int i = 0; i < measurement.size() - 1; i++) { // iterujemy po kolei po pomiarach

			if (measurement.get(i) < measurement.get(i + 1)) // jesli obecne jest mniejsze niz nastepne
				counter++; // zwiekszamy licznik
		}

		System.out.println(counter);

	}
}
