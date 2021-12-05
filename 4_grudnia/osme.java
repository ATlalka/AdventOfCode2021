import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class osme {

	public static void main(String[] args) {

		ArrayList<Integer> numbers = new ArrayList<>();
		ArrayList<Integer[][]> boards = new ArrayList<>();
		ArrayList<Boolean[][]> results = new ArrayList<>();
		ArrayList<Integer> notWonYet = new ArrayList<>();

		File f = new File("bingo.txt");

		try (Scanner s = new Scanner(f)) {

			s.useDelimiter(";|\\r?\\n|\\r");
			String[] allResults = s.next().split("[,]");

			for (int i = 0; i < allResults.length; i++) {

				numbers.add(Integer.parseInt(allResults[i]));
			}

			while (s.hasNext()) {

				s.next();

				Integer[][] currentAnswers = new Integer[5][5];
				Boolean[][] x = new Boolean[5][5];

				for (int i = 0; i < 5; i++) {

					for (int j = 0; j < 5; j++) {
						x[i][j] = false;
					}
				}

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

		int boardNumber = -1;
		int finalNumber = -1;
		int score = 0;

		for (int i = 0; i < boards.size(); i++) {
			notWonYet.add(i + 1);
		}

		for (int i = 0; i < numbers.size(); i++) {

			Integer number = numbers.get(i);

			for (int j = 0; j < boards.size(); j++) {

				if (findNumberInBoard(boards.get(j), number, results.get(j)))

					if (checkIfWinning(results.get(j))) {

						if (notWonYet.indexOf(j + 1) >= 0)
							notWonYet.remove(notWonYet.indexOf(j + 1));

						finalNumber = number;
					}

				if (notWonYet.size() == 1)
					boardNumber = notWonYet.get(0);

				if (notWonYet.size() == 0)
					break;
			}

			if (notWonYet.size() == 0)
				break;
		}

		Boolean[][] finalResult = results.get(boardNumber - 1);
		Integer[][] finalBoard = boards.get(boardNumber - 1);

		for (int i = 0; i < 5; i++) {

			for (int j = 0; j < 5; j++) {

				if (!finalResult[i][j])
					score += finalBoard[i][j];
			}
		}

		System.out.println(finalNumber * score);
	}

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
