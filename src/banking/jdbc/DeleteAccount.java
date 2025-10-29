package banking.jdbc;

public class DeleteAccount extends MyConnection {

	public DeleteAccount(String user, String pass) {
		super(user, pass);
	}

	String query;
	int result;

	@Override
	public void dbExecute() {
		try {

			// 프로시저로 삭제
			query = "{CALL DeleteAccount(?)}";
//			query = "DELETE FROM banking WHERE accountNum=?";

			psmt = con.prepareStatement(query);

			psmt.setString(1, inputValue("삭제할 계좌번호"));
			result = psmt.executeUpdate();

			System.out.println("[psmt]" + result + "계좌가 삭제되었습니다.");

		} catch (Exception e) {
			System.out.println("쿼리 실행 중 예외 발생");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DeleteAccount("project", "1234").dbExecute();
		;
	}

}
