package banking;

import java.io.Serializable;

abstract public class Account implements IAccount, Serializable {
	String accountNum;
	String name;
	int balance;

	public Account(String accountNum, String name, int balane) {
		this.accountNum = accountNum;
		this.name = name;
		this.balance = balane;
	}

	@Override
	public void showAccInfo() {
		System.out.print("계좌번호:" + accountNum);
		System.out.print(", 이름:" + name);
		System.out.println(", 잔고:" + balance);
	}

	@Override
	public void depositMoney(int money) {
	};

	@Override
	public void withdrawMoney(int money) {
		if (this.balance < money) {
			System.out.println("잔고가 부족합니다.금액 전체를 출금할까요?");
			System.out.println("y : 금액 전체 출금 처리");
			System.out.println("n : 출금 요청 취소");

			String reply = BankingSystemMain.scanner.nextLine();

			if (reply.equals("y")) {
				this.balance = 0;
				System.out.println("출금이 완료되었습니다.");
			} else {
				System.out.println("출금이 취소되었습니다.");
			}
			return;
		}
		this.balance -= money;
		System.out.println("출금이 완료되었습니다.");
	}

	@Override
	public int hashCode() {
		int returnCode = accountNum.hashCode();
		return returnCode;
	}

	@Override
	public boolean equals(Object obj) {
		Account account = (Account) obj;
		return accountNum.equals(account.accountNum);
	}
}
