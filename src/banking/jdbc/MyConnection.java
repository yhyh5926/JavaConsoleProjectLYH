package banking.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MyConnection implements IConnect {
	public Connection con;
	public ResultSet rs;
	public Statement stmt;
	public PreparedStatement psmt;
	public CallableStatement csmt;

	public MyConnection(String user, String pass) {
		try {
			Class.forName(ORACLE_DRIVER);
			con = DriverManager.getConnection(ORACLE_URL, user, pass);
		} catch (Exception e) {
			System.out.println("DB 커넥션 예외 발생");
			e.printStackTrace();
		}
	}

	@Override
	public void dbExecute() {
		// 자식 클래스에서 처리될 CRUD를 위한 오버라이드
	}

	@Override
	public void dbClose() {
		try {
			if (con != null)
				con.close();
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (csmt != null)
				csmt.close();
			System.out.println("DB 자원 반납");
		} catch (Exception e) {
			System.out.println("DB 자원 반납 시 예외 발생");
			e.printStackTrace();
		}
	}

	public String inputValue(String title) {

		Scanner scan = new Scanner(System.in);
		System.out.println(title + " 입력(exit->종료):");
		String inputStr = scan.nextLine();

		if ("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다");
			dbClose();
			System.exit(0);
		}
		return inputStr;
	}

}
