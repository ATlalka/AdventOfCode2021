import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class szoste {

	public static void main(String[] args) {

		ArrayList<String> foundOxygen = new ArrayList<>(); // lista oxygen generator rating, ktore obecnie spelniaja
															// kryterium bitowe
		ArrayList<String> foundCO2 = new ArrayList<>(); // lista CO2 scrubber rating, ktore obecnie spelniaja kryterium
														// bitowe

		try (Scanner s = new Scanner(new File("bity.txt"))) {

			s.useDelimiter(";|\\r?\\n|\\r");
			String code;
			while (s.hasNext()) {

				// wczytujemy kolejne liczby binarne i dodajemy do obu list
				code = s.next();
				foundOxygen.add(code);
				foundCO2.add(code);
			}
		}

		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Blad e");
		}

		int length = foundOxygen.get(0).length();
		int oCounter, cCounter;
		int k;

		for (int i = 0; i < length; i++) {

			oCounter = 0;
			cCounter = 0;

			if (foundOxygen.size() > 1) { // jesli lista ma 1 element, to jest to szukany element

				for (int j = 0; j < foundOxygen.size(); j++) {

					if (foundOxygen.get(j).charAt(i) == '1')
						oCounter++;
				}

				// ze wzgledu na warunek >= wprowadzam k
				// poniewaz liczba liczb binarnych w liscie nie zawsze bedzie parzysta

				if (foundOxygen.size() % 2 == 0)
					k = foundOxygen.size() / 2 - 1;

				else
					k = foundOxygen.size() / 2;

				// zgodnie z kryterium na liscie zostaja liczby z odpowiednim bitem na
				// odpowiednim miejscu
				if (oCounter > k) {

					for (int j = 0; j < foundOxygen.size(); j++) {

						if (foundOxygen.get(j).charAt(i) == '0') {
							foundOxygen.remove(j);
							j--;
						}
					}
				}

				else {

					for (int j = 0; j < foundOxygen.size(); j++) {

						if (foundOxygen.get(j).charAt(i) == '1') {
							foundOxygen.remove(j);
							j--;
						}
					}
				}
			}

			if (foundCO2.size() > 1) { // jesli lista ma 1 element, to jest to szukany element

				for (int j = 0; j < foundCO2.size(); j++) {

					if (foundCO2.get(j).charAt(i) == '1')
						cCounter++;
				}

				// ze wzgledu na warunek >= wprowadzam k
				// poniewaz liczba liczb binarnych w liscie nie zawsze bedzie parzysta

				if (foundCO2.size() % 2 == 0)
					k = foundCO2.size() / 2 - 1;

				else
					k = foundCO2.size() / 2;

				// zgodnie z kryterium na liscie zostaja liczby z odpowiednim bitem na
				// odpowiednim miejscu
				if (cCounter <= k) {

					for (int j = 0; j < foundCO2.size(); j++) {

						if (foundCO2.get(j).charAt(i) == '0') {
							foundCO2.remove(j);
							j--;
						}
					}
				}

				else {

					for (int j = 0; j < foundCO2.size(); j++) {

						if (foundCO2.get(j).charAt(i) == '1') {
							foundCO2.remove(j);
							j--;
						}
					}
				}
			}
		}

		int power = 0, oNum = 0, cNum = 0;
		String oxygen = foundOxygen.get(0);
		String CO2 = foundCO2.get(0);

		for (int i = length - 1; i >= 0; i--) {
			// zamiana liczb binarnych na dziesietne
			oNum += Character.getNumericValue(oxygen.charAt(i)) * Math.pow(2, power);
			cNum += Character.getNumericValue(CO2.charAt(i)) * Math.pow(2, power);
			power++;
		}

		System.out.println(oNum * cNum);

	}
}
