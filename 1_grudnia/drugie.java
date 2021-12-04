import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class drugie {

	public static void main(String[] args) {
		ArrayList<Integer> measurements = new ArrayList<Integer>();

		try (Scanner s = new Scanner(new File("pomiary.txt"))) {

			s.useDelimiter(";|\\r?\\n|\\r");

			while (s.hasNext()) {

				measurements.add(s.nextInt());
			}
		}

		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Blad e");
		}

		int counter = 0;

		for (int i = 0; i < measurements.size() - 3; i++) {
			if ((measurements.get(i) + measurements.get(i + 1) + measurements.get(i + 2)) < (measurements.get(i + 1)
					+ measurements.get(i + 2) + measurements.get(i + 3)))
				counter++;
		}

		System.out.println(counter);

	}
}
