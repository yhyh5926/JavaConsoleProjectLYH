package banking.jdbc;

public class ShowAccount extends MyConnection {

	public ShowAccount(String user, String pass) {
		super(user, pass);
	}

	String query;
	int result;

	@Override
	public void dbExecute() {
		try {

			query = "SELECT accountNum, name, TRIM(TO_CHAR(balance, '999,000')) bal, interest FROM banking WHERE accountNum=?";
			psmt = con.prepareStatement(query);

			psmt.setString(1, inputValue("조회할 계좌번호를 입력하세요"));
			rs = psmt.executeQuery();

			while (rs.next()) {
				String accountNum = rs.getString("accountNum");
				String name = rs.getString("name");
				String balance = rs.getString("bal");
				String interest = rs.getString("interest");

				System.out.printf("계좌번호: %s, 이름: %s, 잔고: %s, 이자율: %s \n", accountNum, name, balance, interest);
			}

		} catch (Exception e) {
			System.out.println("쿼리 실행 중 예외 발생");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new ShowAccount("project", "1234").dbExecute();
		;
	}

}
