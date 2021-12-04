import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class szoste {

	public static void main(String[] args) {

		ArrayList<String> foundOxygen = new ArrayList<>();
		ArrayList<String> foundCO2 = new ArrayList<>();

		try (Scanner s = new Scanner(new File("bity.txt"))) {

			s.useDelimiter(";|\\r?\\n|\\r");
			String code;
			while (s.hasNext()) {
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

			if (foundOxygen.size() > 1) {

				for (int j = 0; j < foundOxygen.size(); j++) {
					if (foundOxygen.get(j).charAt(i) == '1')
						oCounter++;
				}

				if (foundOxygen.size() % 2 == 0)
					k = foundOxygen.size() / 2 - 1;
				else
					k = foundOxygen.size() / 2;

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

			if (foundCO2.size() > 1) {

				for (int j = 0; j < foundCO2.size(); j++) {
					if (foundCO2.get(j).charAt(i) == '1')
						cCounter++;
				}

				if (foundCO2.size() % 2 == 0)
					k = foundCO2.size() / 2 - 1;
				else
					k = foundCO2.size() / 2;

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
			oNum += Character.getNumericValue(oxygen.charAt(i)) * Math.pow(2, power);
			cNum += Character.getNumericValue(CO2.charAt(i)) * Math.pow(2, power);
			power++;
		}

		System.out.println(oNum * cNum);
    
	}
}
