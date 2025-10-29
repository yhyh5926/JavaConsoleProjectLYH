package banking.jdbc;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {
	public static Scanner scanner = new Scanner(System.in);

	public static void showMenu() {
		System.out.println("---------Menu---------");
		System.out.print("1.계좌개설 ");
		System.out.print(", 2.입금 ");
		System.out.print(", 3.출금 ");
		System.out.println("4.전체계좌정보출력");
		System.out.print("5.계좌정보삭제");
		System.out.print(", 6.계좌정보출력");
		System.out.println(", 7.프로그램 종료");
	}

	public static void main(String[] args) throws IOException {

		InsertAccount insertAccount = new InsertAccount("project", "1234");
		DepositAccount depositAccount = new DepositAccount("project", "1234");
		WithdrawAccount withdrawAccount = new WithdrawAccount("project", "1234");
		ShowAllAccount showAllAccount = new ShowAllAccount("project", "1234");
		ShowAccount showAccount = new ShowAccount("project", "1234");
		DeleteAccount deleteAccount = new DeleteAccount("project", "1234");

		while (true) {
			// 메뉴 표시
			showMenu();

			try {
				// 메뉴 선택
				int choice = scanner.nextInt();
				scanner.nextLine();

				// 지정된 숫자 이외 입력 시 에러
				if (choice < ICustomDefine.MAKE || choice > ICustomDefine.EXIT) {
					throw new MenuSelectException();
				}

				switch (choice) {
				case ICustomDefine.MAKE:
					insertAccount.dbExecute();
					break;
				case ICustomDefine.DEPOSIT:
					depositAccount.dbExecute();
					break;
				case ICustomDefine.WITHDRAW:
					withdrawAccount.dbExecute();
					break;
				case ICustomDefine.INQUIRE:
					showAllAccount.dbExecute();
					break;
				case ICustomDefine.DELETE:
					deleteAccount.dbExecute();
					break;
				case ICustomDefine.SHOW_INFO:
					showAccount.dbExecute();
					break;
				case ICustomDefine.EXIT:
					System.out.println("프로그램 종료");
					return;
				}

			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.err.println("숫자만 입력하시오");
				continue;
			} catch (MenuSelectException e) {
				System.err.println(e.getMessage());
				System.err.println("1~7 사이의 숫자를 입력하세요.");
			}

		}
	}

}
