package banking.jdbc;

public interface IConnect {
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";

	void dbExecute();

	void dbClose();

	String inputValue(String title);
}
