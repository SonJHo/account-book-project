import java.io.*;

public class AccountingMain {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FileManager fm = new FileManager();
        fm.updateValues();

        exit:
        while (true) {
            System.out.println("---Menu---");
            System.out.println("1) 구매 내역 추가");
            System.out.println("2) 잔고 금액 추가");
            System.out.println("3) 잔고 금액 차감");
            System.out.println("4) 잔고 보기");
            System.out.println("RESET)초기화");
            System.out.println("EXIT) 종료");
            System.out.println("----------");
            System.out.print("입력:");

            String MenuIntInput = "";
            try {
                MenuIntInput = br.readLine().strip();
            } catch (IOException e) {
                System.out.println("menu input error!!");
            }

            switch (MenuIntInput) {
                case "1":
                    fm.writeAtReceipt(br, fm);
                    break;
                case "2":
                    Account.deposit(br, fm);
                    break;
                case "3":
                    Account.withdraw(br, fm);
                    break;
                case "4":
                    Account.printBalance();
                    break;
                case "EXIT":
                    System.out.println("program close...");
                    break exit;
                case "RESET":
                    fm.reset();
                    break;
                default:
                    System.out.println("MenuIntInput out-of-range error!");
                    break;
            }
        }

        try {
            br.close();
        } catch (IOException e) {
            System.out.println("고장");
            throw new RuntimeException(e);
        }
    }
}
