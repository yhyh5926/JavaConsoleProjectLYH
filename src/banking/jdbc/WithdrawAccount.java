package banking.jdbc;

public class WithdrawAccount extends MyConnection {

	public WithdrawAccount(String user, String pass) {
		super(user, pass);
	}

	String query;
	int result;

	@Override
	public void dbExecute() {
		try {
			String accountNum = inputValue("출금할 계좌번호");
			// 현재 잔액 조회
			query = "SELECT balance FROM banking WHERE accountNum=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, accountNum);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int currentBalance = rs.getInt(1);
				int withdraw = Integer.parseInt(inputValue("출금할 금액"));

				if (currentBalance < withdraw) {
					System.out.println("잔액이 부족해 출금할 수 없습니다.");
				} else {
					query = "UPDATE banking SET balance=balance-? WHERE accountNum=?";

					psmt = con.prepareStatement(query);

					psmt.setInt(1, withdraw);
					psmt.setString(2, accountNum);
					
					result = psmt.executeUpdate();
					System.out.println("[psmt]" + result + "출금되었습니다.");
				}

			}

		} catch (Exception e) {
			System.out.println("쿼리 실행 중 예외 발생");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new WithdrawAccount("project", "1234").dbExecute();
	}

}
