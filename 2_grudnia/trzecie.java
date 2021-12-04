import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class trzecie {
	public static void main(String[] args) {

		int depth = 0, horizontal = 0;

		try (Scanner s = new Scanner(new File("komendy.txt"))) {

			s.useDelimiter(";|\\r?\\n|\\r");

			while (s.hasNext()) {
				String[] tab = s.next().split(" ");
				switch (tab[0]) {

				case "forward":
					horizontal += Integer.parseInt(tab[1]);
					break;

				case "up":
					depth -= Integer.parseInt(tab[1]);
					break;

				case "down":
					depth += Integer.parseInt(tab[1]);
					break;
				}
			}
		}

		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Blad e");
		}

		System.out.println(depth * horizontal);

	}
}
