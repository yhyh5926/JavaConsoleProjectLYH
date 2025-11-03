package banking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Set;


public class AccountManager {

	public Set<Account> accounts;

	public AccountManager() {
		accounts = new HashSet<>();
	}

	public void saveAccount() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/banking/AccountInfo.obj"));
			out.writeObject(accounts);
			out.close();
			System.out.println("AccountInfo.obj에 저장됨");
		} catch (FileNotFoundException e) {
			System.out.println("[예외]파일없음");
		} catch (Exception e) {
			System.out.println("[Exeption]뭔가없음");
		}
	}

	public void loadAccount() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/banking/AccountInfo.obj"));
			accounts = (Set<Account>) in.readObject();
			in.close();
			System.out.println("AccountInfo.obj에서 로드됨");
		} catch (FileNotFoundException e) {
			System.out.println("[예외]파일없음");
		} catch (Exception e) {
			System.out.println("[Exeption]뭔가없음");
		}
	}

	public void checkDuplicateAccount(Account account) {
		if (accounts.contains(account)) {
			System.out.println("중복계좌발견됨, 덮어쓸까요? (y or n)");
			String reply = BankingSystemMain.scanner.nextLine();

			if (reply.equals("y")) {
				accounts.remove(account);
				accounts.add(account);
				System.out.println("계좌정보를 덮어씌웠습니다.");
			} else {
				System.out.println("취소되었습니다.");
			}
			return;
		} else {
			accounts.add(account);
		}
	}

	public void makeAccount() {
		System.out.println("***신규계좌개설***");
		System.out.println("----계좌선택----");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.println("3.특판계좌");

		try {
			int choice = BankingSystemMain.scanner.nextInt();
			BankingSystemMain.scanner.nextLine();

			if (choice != 1 && choice != 2 && choice != 3) {
				System.err.println("예외 처리 발생");
				System.err.println("1 ,2 ,3 중에 고르시오");
				return;
			}

			String iAccountNum, iName;
			int iBalance;
			System.out.print("계좌번호: ");
			iAccountNum = BankingSystemMain.scanner.nextLine();
			System.out.print("이름: ");
			iName = BankingSystemMain.scanner.nextLine();
			System.out.print("잔고: ");
			iBalance = BankingSystemMain.scanner.nextInt();

			switch (choice) {
			case 1: {
				System.out.print("기본이자%(정수형태로입력):");
				int iInterest = BankingSystemMain.scanner.nextInt();
				BankingSystemMain.scanner.nextLine();
				NormalAccount newAccount = new NormalAccount(iAccountNum, iName, iBalance, iInterest);
				checkDuplicateAccount(newAccount);
				break;
			}
			case 2: {
				System.out.print("기본이자%(정수형태로입력):");
				int iInterest = BankingSystemMain.scanner.nextInt();
				BankingSystemMain.scanner.nextLine();
				System.out.print("신용등급(A,B,C등급):");
				String iCreditScore = BankingSystemMain.scanner.nextLine();
				HighCreditAccount newAccount = new HighCreditAccount(iAccountNum, iName, iBalance, iInterest,
						iCreditScore);
				checkDuplicateAccount(newAccount);
				break;
			}
			case 3: {
				System.out.print("기본이자%(정수형태로입력):");
				int iInterest = BankingSystemMain.scanner.nextInt();
				BankingSystemMain.scanner.nextLine();
				SpecialAccount newAccount = new SpecialAccount(iAccountNum, iName, iBalance, iInterest);
				checkDuplicateAccount(newAccount);
				break;
			}
			}

			System.out.println("완료되었습니다.");
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력하시오");
			BankingSystemMain.scanner.nextLine();
		}

	}

	public Account searchAcc() {
		System.out.print("계좌번호:");
		String searchAccountNum = BankingSystemMain.scanner.nextLine();

		for (Account account : accounts) {

			if (account.accountNum.equals(searchAccountNum)) {
				return account;
			}

		}
		return null;

	}

	public void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요");

		Account searchAcc = searchAcc();

		if (searchAcc != null) {
			try {
				System.out.print("입금액:");
				int money = BankingSystemMain.scanner.nextInt();
				if (money < 0) {
					System.out.println("음수는 입금할 수 없습니다");
				} else {
					if (money % 500 != 0)
						System.out.println("입금액은 500원 단위로만 가능합니다");
					else {
						// 특판계좌 시 이자
						if (searchAcc instanceof SpecialAccount) {
							SpecialAccount sa = (SpecialAccount) searchAcc;
							sa.depositMoney(money);
							// 신뢰계좌 시 이자
						} else if (searchAcc instanceof HighCreditAccount) {

							HighCreditAccount ha = (HighCreditAccount) searchAcc;
							ha.depositMoney(money);
							// 일반계좌 시 이자
						} else if (searchAcc instanceof NormalAccount) {
							NormalAccount na = (NormalAccount) searchAcc;
							na.depositMoney(money);
						}
						System.out.println("입금이 완료되었습니다.");
					}
				}
			}

			catch (InputMismatchException e) {
				System.out.println("숫자를 입력하시오");
				BankingSystemMain.scanner.nextLine();
			}
		} else {
			System.err.println("일치하는 계좌가 없습니다.");
		}

	}

	public void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요");

		Account searchAcc = searchAcc();
		if (searchAcc == null) {
			System.out.println("일치하는 계좌가 없습니다.");
			return;
		}

		try {
			System.out.print("출금액:");
			int money = BankingSystemMain.scanner.nextInt();
			BankingSystemMain.scanner.nextLine();

			if (money < 0) {
				System.out.println("음수는 출금할 수 없습니다.");
				return;
			}

			if (money % 1000 != 0) {
				System.out.println("출금은 1000원 단위로만 가능합니다.");
				return;
			}

			searchAcc.withdrawMoney(money);

		} catch (InputMismatchException e) {
			System.out.println("숫자를 입력하시오");
			BankingSystemMain.scanner.nextLine();
		}

	}

	public void deleteAccount() {
		System.out.println("***계좌정보삭제***");
		System.out.print("계좌번호 : ");
		String iAccount = BankingSystemMain.scanner.nextLine();

		Iterator<Account> iterator = accounts.iterator();
		boolean find = false;

		while (iterator.hasNext()) {
			Account account = iterator.next();
			if (account.accountNum.equals(iAccount)) {
				iterator.remove();
				System.out.println("계좌를 삭제하였습니다.");
				find = true;
				break;
			}
		}

		if (!find) {
			System.out.println("일치하는 계좌가 없습니다.");
		}
	}

	public void showAccInfo() {
		System.out.println("***계좌정보출력***");

		if (accounts.size() == 0) {
			System.out.println("등록된 계좌가 없습니다.");
			return;
		} else {
			for (Account account : accounts) {
				if (account != null) {
					System.out.println("----------------");
					account.showAccInfo();
					System.out.println("----------------");
				}
			}
		}

		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
}
