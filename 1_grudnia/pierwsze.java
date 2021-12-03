import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class pierwsze {

	public static void main(String[] args) {
		
		ArrayList<Integer> pomiary = new ArrayList<Integer>();
		
		try (Scanner s = new Scanner(new File("pomiary.txt"))) {

			//s.nextLine();

			s.useDelimiter(";|\\r?\\n|\\r");

			while (s.hasNext()) {

				pomiary.add(s.nextInt());
			}
		}

		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Blad e");
		}
		
		int licznik = 0;
		
		for (int i = 0; i < pomiary.size() - 1; i++) {
			if (pomiary.get(i) < pomiary.get(i+1))
				licznik++;
		}
		
		System.out.println(licznik);

	}

}
