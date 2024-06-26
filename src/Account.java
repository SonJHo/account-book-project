import java.io.BufferedReader;
import java.io.IOException;

public class Account {
    private static int balance;

    public static int getBalance() {
        return balance;
    }

    public static void setBalance(int balance) {
        Account.balance = balance;
    }

    public static void deposit(BufferedReader br, FileManager fm) {

        System.out.print("잔고에 추가할 금액 입력:");
        try {
            int money = Integer.parseInt(br.readLine());
            balance += money;
            fm.writeDepositLog(money);
            System.out.println("deposit..");
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("Not number error!!");
        }
    }

    public static void withdraw(BufferedReader br, FileManager fm) {

        System.out.print("잔고서 차감할 금액 입력:");
        int money = 0;
        try {
            money = Integer.parseInt(br.readLine());
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("Not number error!!");
        }
        balance -= money;
        fm.writeWithdrawLog(money);
    }
    public static void withdraw(int money, FileManager fm){
        balance -= money;
        fm.writeWithdrawLog(money);
    }

    public static void resetBalance() {
        balance = 0;
        System.out.println("balance reset..");
    }

    public static void printBalance() {
        System.out.println("current balance:" + balance);
    }
}
