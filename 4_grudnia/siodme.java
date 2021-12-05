import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class siodme {

	public static void main(String[] args) {

		ArrayList<Integer> numbers = new ArrayList<>(); // kolejne wygrywajace liczby
		ArrayList<Integer[][]> boards = new ArrayList<>(); // kolejne plansze
		ArrayList<Boolean[][]> results = new ArrayList<>(); // skreslenia na kolejnych planszach

		File f = new File("bingo.txt");

		try (Scanner s = new Scanner(f)) {

			s.useDelimiter(";|\\r?\\n|\\r");

			// pobieram pierwsza linie pliku, ktora oznacza kolejne wygrywajace liczby
			// usuwam przecinki i zapisuje liczby do listy
			String[] allResults = s.next().split("[,]");

			for (int i = 0; i < allResults.length; i++) {

				numbers.add(Integer.parseInt(allResults[i]));
			}

			while (s.hasNext()) {

				s.next();

				Integer[][] currentAnswers = new Integer[5][5];
				Boolean[][] x = new Boolean[5][5];

				// ustawiam skreslenia na planszach na false
				for (int i = 0; i < 5; i++) {

					for (int j = 0; j < 5; j++) {

						x[i][j] = false;
					}
				}

				// wczytuje kolejne plansze i dodaje je do listy wraz z wyzerowanymi
				// skresleniami
				for (int i = 0; i < 5; i++) {

					String line = s.next();

					String[] currentLine = line.split(" ");

					int counter = 0;

					for (int j = 0; j < currentLine.length; j++) {

						if (!currentLine[j].isEmpty()) {
							currentAnswers[i][counter] = Integer.parseInt(currentLine[j]);
							counter++;
						}
					}
				}

				boards.add(currentAnswers);
				results.add(x);

			}
		}

		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Blad e");
		}

		int boardNumber = -1; // numer wygrywajacej planszy
		int finalNumber = -1; // numer, dzieki ktoremu plansza wygrala
		int score = 0;

		// iteruje po kolei po liczbach
		for (int i = 0; i < numbers.size(); i++) {

			Integer number = numbers.get(i);

			// iteruje po kolei po planszach
			for (int j = 0; j < boards.size(); j++) {

				// sprawdzam, czy liczba jest na planszy i jesli tak to ja skreslam i sprawdzam
				// czy plansza jest wygrywajaca
				if (findNumberInBoard(boards.get(j), number, results.get(j)))

					if (checkIfWinning(results.get(j))) {
						boardNumber = j + 1; // bo plansze numerowane od 1
						finalNumber = number;
					}

				if (boardNumber > 0) // jesli mamy numer wygrywajacej planszy to nie musimy dalej szukac
					break;
			}

			if (boardNumber > 0) // jesli mamy numer wygrywajacej planszy to nie musimy dalej szukac
				break;
		}

		Boolean[][] finalResult = results.get(boardNumber - 1);
		Integer[][] finalBoard = boards.get(boardNumber - 1);

		// zliczam sume nieskreslonych liczb
		for (int i = 0; i < 5; i++) {

			for (int j = 0; j < 5; j++) {

				if (!finalResult[i][j])
					score += finalBoard[i][j];
			}
		}

		System.out.println(finalNumber * score);
	}

	// sprawdza, czy plansza wygrala
	static boolean checkIfWinning(Boolean[][] board) {

		boolean ifColumn = true;
		boolean ifRow = true;

		for (int i = 0; i < 5; i++) {

			ifRow = true;

			for (int j = 0; j < 5; j++) {

				if (!board[i][j]) {

					ifRow = false;
					break;
				}
			}

			if (ifRow)
				return true;
		}

		for (int i = 0; i < 5; i++) {

			ifColumn = true;

			for (int j = 0; j < 5; j++) {

				if (!board[j][i]) {

					ifColumn = false;
					break;
				}
			}

			if (ifColumn)
				return true;
		}

		return false;
	}

	// szuka liczby na planszy i ja odznacza
	static boolean findNumberInBoard(Integer[][] board, int number, Boolean[][] result) {

		boolean atLeastOne = false;

		for (int i = 0; i < 5; i++) {

			for (int j = 0; j < 5; j++) {

				if (board[i][j] == number) {
					result[i][j] = true;
					atLeastOne = true;
				}
			}
		}

		return atLeastOne;
	}
}
