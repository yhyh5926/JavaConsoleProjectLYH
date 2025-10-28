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

	public String getAccountNum() {
		return accountNum;
	}

}
