package banking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class AutoSaver extends Thread {

	private Set<Account> accounts;

	public AutoSaver(Set<Account> accounts) {
		this.accounts = accounts;
		setDaemon(true); // 프로그램 종료 시 쓰레드도 종료된느 데몬쓰레드
	}

	@Override
	public void run() {
		while (true) {
			try {
				autoSave(accounts);
				sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("자동저장 종료됨");
				break;
			} catch (IOException e) {
				System.out.println("자동저장 예외 발생");
			}
		}
	}

	public void autoSave(Set<Account> accounts) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("src/banking/AutoSaveAccount.txt"));

		for (Account account : accounts) {
			if (account instanceof SpecialAccount) {
				SpecialAccount sa = (SpecialAccount) account;
				out.print("[보통계좌]");
				out.print("계좌번호:" + sa.accountNum);
				out.print(", 이름:" + sa.name);
				out.print(", 잔고:" + sa.balance);
				out.print(", 이자:" + sa.interest);
				out.println(", 입금 회차:" + sa.interest);
			} else if (account instanceof NormalAccount) {
				NormalAccount na = (NormalAccount) account;
				out.print("[보통계좌]");
				out.print("계좌번호:" + na.accountNum);
				out.print(", 이름:" + na.name);
				out.print(", 잔고:" + na.balance);
				out.println(", 이자:" + na.interest);
			} else {
				HighCreditAccount ha = (HighCreditAccount) account;
				out.print("[신용신뢰계좌]");
				out.print("계좌번호:" + ha.accountNum);
				out.print(", 이름:" + ha.name);
				out.print(", 잔고:" + ha.balance);
				out.print(", 이자:" + ha.interest);
				out.println(" 신용등급:" + ha.creditScore);
			}
		}

		out.close();
		System.out.println("AutoSaveAccount.txt가 저장되었습니다.");
	}

}
