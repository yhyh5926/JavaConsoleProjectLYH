package banking.threeby3;

public class SpecialAccount extends NormalAccount {
	int count;

	public SpecialAccount(String accountNum, String name, int balance, int interest) {
		super(accountNum, name, balance, interest);
		this.count = 0;
	}

	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("입금 회차: " + count + "회");
	}

	@Override
	public void depositMoney(int money) {
		this.count++;
		int bonus = 500;

		if (this.count % 2 == 0) {
			this.balance += (this.balance * this.interest / 100) + money + bonus;
			System.out.println("짝수 번째 입금 보너스 + " + bonus);
		} else {
			this.balance += (this.balance * this.interest / 100) + money + bonus;
		}
	}

}
