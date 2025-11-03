package banking;

public class HighCreditAccount extends Account {

	int interest;
	String creditScore;

	public HighCreditAccount(String accountNum, String name, int balane, int interest, String creditScore) {
		super(accountNum, name, balane);
		this.interest = interest;
		this.creditScore = creditScore;
	}

	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.print("기본이자%:" + interest);
		System.out.println(", 신용등급:" + creditScore);
	}

	@Override
	public void depositMoney(int money) {
		this.balance += (this.balance * this.interest / 100) + (this.balance * this.creditScoreToNumber() / 100)
				+ money;
	}

	public double creditScoreToNumber() {
		if (creditScore == "A")
			return ICustomDefine.A;
		else if (creditScore == "B")
			return ICustomDefine.B;

		return ICustomDefine.C;
	}

}
