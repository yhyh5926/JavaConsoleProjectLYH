package banking;

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

}
