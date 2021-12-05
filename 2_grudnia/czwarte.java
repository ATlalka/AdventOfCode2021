import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class czwarte {

	public static void main(String[] args) {
		int depth = 0, horizontal = 0, aim = 0;

		try (Scanner s = new Scanner(new File("komendy.txt"))) {

			s.useDelimiter(";|\\r?\\n|\\r");

			while (s.hasNext()) {

				// wczytujemy kolejne komendy i odpowiednio modyfikujemy depth, aim i horizontal
				String[] tab = s.next().split(" ");
				switch (tab[0]) {

				case "forward":
					horizontal += Integer.parseInt(tab[1]);
					depth += Integer.parseInt(tab[1]) * aim;
					break;

				case "up":
					aim -= Integer.parseInt(tab[1]);
					break;

				case "down":
					aim += Integer.parseInt(tab[1]);
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
