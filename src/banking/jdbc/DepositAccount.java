package banking.jdbc;

public class DepositAccount extends MyConnection {

	public DepositAccount(String user, String pass) {
		super(user, pass);
	}

	String query;
	int result;

	@Override
	public void dbExecute() {
		try {

			query = "UPDATE banking SET balance=balance+(balance*interest)+? WHERE accountNum=?";

			psmt = con.prepareStatement(query);

			psmt.setString(2, inputValue("입금할 계좌번호"));
			psmt.setInt(1, Integer.parseInt(inputValue("입금할 금액")));

			result = psmt.executeUpdate();

			System.out.println("[psmt]" + result + "입금되었습니다.");

		} catch (Exception e) {
			System.out.println("쿼리 실행 중 예외 발생");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DepositAccount("project", "1234").dbExecute();
	}

}
