package banking.jdbc;

import java.sql.SQLException;

public class InsertAccount extends MyConnection {

	public InsertAccount(String user, String pass) {
		super(user, pass);
	}

	String query;
	int result;

	@Override
	public void dbExecute() {
		try {
			query = "INSERT INTO banking VALUES " + " (seq_banking_idx.NEXTVAL,?,?,?,?)";

			psmt = con.prepareStatement(query);
			psmt.setString(1, inputValue("계좌번호"));
			psmt.setString(2, inputValue("이름"));
			psmt.setString(3, inputValue("잔고"));
			psmt.setString(4, inputValue("이자율"));

			result = psmt.executeUpdate();
			System.out.println("[psmt]" + result + "행 입력됨");

		} catch (SQLException e) {
			if (e.getErrorCode() == 1) { // ORA-00001
				System.out.println("중복된 계좌번호입니다. 다른 계좌번호를 입력하세요.");
			} else {
				System.out.println("쿼리 실행 중 예외 발생");
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		System.out.println("계좌시스템 시작");
		new InsertAccount("project", "1234").dbExecute();

	}

}
