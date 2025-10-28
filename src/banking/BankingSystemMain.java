package banking;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {
	public static Scanner scanner = new Scanner(System.in);

	public static void showMenu() {
		System.out.println("---------Menu---------");
		System.out.print("1.계좌계설 ");
		System.out.print(", 2.입금 ");
		System.out.println(", 3.출금 ");
		System.out.print("4.전체계좌정보출력");
		System.out.print(", 5.계좌정보삭제");
		System.out.print(", 6.저장옵션");
		System.out.println(", 7.프로그램 종료");
	}

	public static void main(String[] args) throws IOException {

		// 어카운트 매니저 생성
		AccountManager accountManager = new AccountManager();
		// AccountInfo.obj 데이터 로드
		accountManager.loadAccount();

		while (true) {
			// 메뉴 표시
			showMenu();

			try {
				//메뉴 선택
				int choice = scanner.nextInt();
				scanner.nextLine();

				//지정된 숫자 이외 입력 시 에러
				if (choice < ICustomDefine.MAKE || choice > ICustomDefine.EXIT) {
					throw new MenuSelectException();
				}

				switch (choice) {
				case ICustomDefine.MAKE:
					accountManager.makeAccount();
					break;
				case ICustomDefine.DEPOSIT:
					accountManager.depositMoney("입금");
					break;
				case ICustomDefine.WITHDRAW:
					accountManager.withdrawMoney("출금");
					break;
				case ICustomDefine.INQUIRE:
					accountManager.showAccInfo();
					break;
				case ICustomDefine.DELETE:
					accountManager.deleteAccount();
					break;
				case ICustomDefine.SAVE_OPTION:
					accountManager.saveOption();
					break;
				case ICustomDefine.EXIT:
					System.out.println("프로그램 종료");
					accountManager.saveAccount();
					return;
				}

			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.err.println("숫자만 입력하시오");
				continue;
			} catch (MenuSelectException e) {
				System.err.println(e.getMessage());
				System.err.println("1~6 사이의 숫자를 입력하세요.");
			}

		}
	}

}
