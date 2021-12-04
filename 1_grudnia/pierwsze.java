import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class pierwsze {

	public static void main(String[] args) {

		ArrayList<Integer> measurement = new ArrayList<Integer>();

		try (Scanner s = new Scanner(new File("pomiary.txt"))) {

			s.useDelimiter(";|\\r?\\n|\\r");

			while (s.hasNext()) {

				measurement.add(s.nextInt());
			}
		}

		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Blad e");
		}

		int counter = 0;

		for (int i = 0; i < measurement.size() - 1; i++) {
			if (measurement.get(i) < measurement.get(i + 1))
				counter++;
		}

		System.out.println(counter);

	}
}
