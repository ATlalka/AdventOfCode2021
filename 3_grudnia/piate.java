import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class piate {

	public static void main(String[] args) {

		int length;
		byte[] gamma;
		byte[] epsilon;
		int counter = 0;

		ArrayList<Integer> occurence = new ArrayList<>();

		try (Scanner s = new Scanner(new File("bity.txt"))) {

			s.useDelimiter(";|\\r?\\n|\\r");

			String code = s.next();
			counter++;

			length = code.length();

			gamma = new byte[length];
			epsilon = new byte[length];

			for (int i = 0; i < length; i++) {
				occurence.add(0);
			}

			for (int i = 0; i < length; i++) {
				if (code.charAt(i) == '1') {
					occurence.set(i, occurence.get(i) + 1);
				}
			}

			while (s.hasNext()) {
				code = s.next();
				counter++;

				for (int i = 0; i < length; i++) {
					if (code.charAt(i) == '1') {
						occurence.set(i, occurence.get(i) + 1);
					}
				}
			}

			for (int i = 0; i < length; i++) {
				if (occurence.get(i) > counter / 2) {
					gamma[i] = (byte) (gamma[i] + 1);
				}

				else {
					epsilon[i] = (byte) (epsilon[i] + 1);
				}
			}

			int gammaNum = 0, epsilonNum = 0;

			int power = 0;

			for (int i = length - 1; i >= 0; i--) {
				gammaNum += (byte) gamma[i] * Math.pow(2, power);
				epsilonNum += (byte) epsilon[i] * Math.pow(2, power);
				power++;
			}

			System.out.println(gammaNum * epsilonNum);
		}

		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Blad e");
		}
	}
}
