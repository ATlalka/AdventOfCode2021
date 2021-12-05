import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class drugie {

	public static void main(String[] args) {
		ArrayList<Integer> measurements = new ArrayList<Integer>();

		try (Scanner s = new Scanner(new File("pomiary.txt"))) {

			s.useDelimiter(";|\\r?\\n|\\r");

			while (s.hasNext()) { // wczytujemy po kolei pomiary

				measurements.add(s.nextInt()); // i zapisujemy do listy
			}
		}

		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Blad e");
		}

		int counter = 0;

		for (int i = 0; i < measurements.size() - 3; i++) {

			// jesli aktualny pomiar zsumowany z 2 nastepnymi jest mniejszy niz suma 3
			// nastepnych
			if ((measurements.get(i) + measurements.get(i + 1) + measurements.get(i + 2)) < (measurements.get(i + 1)
					+ measurements.get(i + 2) + measurements.get(i + 3)))
				counter++; // zwiekszamy licznik
		}

		System.out.println(counter);

	}
}
