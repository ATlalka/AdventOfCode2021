import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class drugie {

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
		
		for (int i = 0; i < pomiary.size() - 3; i++) {
			if ((pomiary.get(i) + pomiary.get(i+1) + pomiary.get(i+2))< (pomiary.get(i+1) + pomiary.get(i+2) + pomiary.get(i+3)))
				licznik++;
		}
		
		System.out.println(licznik);


	}

}
