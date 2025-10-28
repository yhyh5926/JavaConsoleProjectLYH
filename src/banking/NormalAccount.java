package banking;

public class NormalAccount extends Account {
	int interest;

	public NormalAccount(String accountNum, String name, int balane, int interest) {
		super(accountNum, name, balane);
		this.interest = interest;
	}

	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자%:" + interest);
	}

	@Override
	public int hashCode() {
		int returnCode = getAccountNum().hashCode();
		return returnCode;
	}

	@Override
	public boolean equals(Object obj) {
		Account account = (Account) obj;
		return getAccountNum().equals(account.getAccountNum());
	}
}
