package banking.threeby3;

import java.util.Random;

interface Key {
	String UP = "w";
	String DOWN = "s";
	String LEFT = "a";
	String RIGHT = "d";
}

public class Puzzle {

	// 셔플할 횟수
	int COUNT = 100;
	// 입력한 횟수
	int inputCount = 0;

	public int[] getXIndex(String[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if ("x".equals(arr[i][j])) {
					return new int[] { i, j };
				}
			}
		}
		return null;
	}

	public void handleMove(String[][] arr, int dx, int dy) {
		int[] position = getXIndex(arr);
		int x = position[0];
		int y = position[1];

		int newX = x + dx;
		int newY = y + dy;

		if (x >= 0 && x < arr.length && y >= 0 && y < arr.length && newX >= 0 && newX < arr.length && newY >= 0
				&& newY < arr.length) {
			String temp = arr[newX][newY];
			arr[x][y] = temp;
			arr[newX][newY] = "x";
		} else {
			System.out.println("xxxxxxxxxxxxxxxxx");
			System.out.println("xxxxx이 동 불 가xxxx");
			System.out.println("xxxxxxxxxxxxxxxxx");
			System.out.println("다시 입력해주세요");
		}
	}

	public boolean isMatch(String[][] arr) {
		String str = "";
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				str += (String) arr[i][j];
			}
		}
		if (str.equals("12345678x"))
			return true;
		else
			return false;
	}

	public String[][] handleShuffle(String[][] arr) {

		int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		Random random = new Random();

		for (int i = 0; i < COUNT; i++) {
			int[] dir = direction[random.nextInt(direction.length)]; // 4가지 중 하나 뽑기
			int[] position = getXIndex(arr);
			int x = position[0];
			int y = position[1];

			int newX = x + dir[0];
			int newY = y + dir[1];

			if (x >= 0 && x < arr.length && y >= 0 && y < arr.length && newX >= 0 && newX < arr.length && newY >= 0
					&& newY < arr.length) {
				String temp = arr[newX][newY];
				arr[x][y] = temp;
				arr[newX][newY] = "x";
			}
		}
		return arr;
	}

	public void puzzleStart() {

		String[][] initArr = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "x" } };
		String[][] shuffledArr = handleShuffle(initArr);

		while (true) {

			System.out.println("==================");
			for (int i = 0; i < shuffledArr.length; i++) {
				for (int j = 0; j < shuffledArr.length; j++) {
					if (j == 2) {
						System.out.println(" " + shuffledArr[i][j]);
					} else
						System.out.print(" " + shuffledArr[i][j]);
				}
			}
			System.out.println("==================");

			if (isMatch(shuffledArr)) {
				System.out.println("==^^정답입니다^^==");
				System.out.println("입력한 총 횟수 " + inputCount);
				System.out.println("재시작하시겠습니까?(y누르면 재시작, 나머지는 종료)");
				String key = BankingSystemMain.scanner.nextLine();
				if (key.equals("y")) {
					shuffledArr = handleShuffle(
							new String[][] { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "x" } });
					continue;
				} else {
					System.out.println("게임을 종료합니다.");
					return;
				}
			}

			System.out.print("키를 입력해주세요:");
			String key = BankingSystemMain.scanner.nextLine();
			switch (key) {
			case Key.UP:
				handleMove(shuffledArr, 1, 0);
				inputCount++;
				System.out.println("입력한 횟수 " + inputCount);
				break;
			case Key.DOWN:
				handleMove(shuffledArr, -1, 0);
				inputCount++;
				System.out.println("입력한 횟수 " + inputCount);
				break;
			case Key.LEFT:
				handleMove(shuffledArr, 0, 1);

				inputCount++;
				System.out.println("입력한 횟수 " + inputCount);
				break;
			case Key.RIGHT:
				handleMove(shuffledArr, 0, -1);
				inputCount++;
				System.out.println("입력한 횟수 " + inputCount);
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}

		}

	}
}
