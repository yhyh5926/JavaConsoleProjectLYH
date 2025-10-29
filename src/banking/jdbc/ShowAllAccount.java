package banking.jdbc;

import java.sql.SQLException;

public class ShowAllAccount extends MyConnection {

	public ShowAllAccount(String user, String pass) {
		super(user, pass);
	}

	String query;
	int result;

	@Override
	public void dbExecute() {
		try {
			stmt = con.createStatement();
			query = "SELECT accountNum, name, TRIM(TO_CHAR(balance, '999,000')) bal, interest FROM banking ORDER BY id";

			rs = stmt.executeQuery(query);

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
		new ShowAllAccount("project", "1234").dbExecute();
	}

}
