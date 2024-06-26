import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class FileManager {
    private static final String logFilePath = "C:\\Users\\user\\Desktop\\AccountingProject\\src\\log.txt";
    private static final String receiptFilePath = "C:\\Users\\user\\Desktop\\AccountingProject\\src\\receipt.txt";

    private String name;
    private int price;
    private int quantity;

    public void writeAtReceipt(BufferedReader br, FileManager fm) {
        StringTokenizer st;
        System.out.println("\t물품/가격/수량");
        System.out.print("입력:");

        try (FileWriter fw = new FileWriter(receiptFilePath, true)) {
            st = new StringTokenizer(br.readLine(),"/");
            name = st.nextToken().strip();
            price = Integer.parseInt(st.nextToken().strip());
            quantity = Integer.parseInt(st.nextToken().strip());
            int priceSum = price * quantity;

            //fw.write(String.format("%-20s%10s%10s%10s\n\n", "Product", "Quantity", "Price", "PriceSum"));
            fw.write(String.format("%-50s%8s%4s%10s\n", name, price, quantity, priceSum));


            writePurchaseLog();
            Account.withdraw(priceSum, fm);
            System.out.println("=>작성 완료");

        } catch (Exception e) {
            System.out.println("InputFormat Error!!!");
        }

    }

    public void writeDepositLog(int money){
        try (FileWriter fw = new FileWriter(logFilePath, true)) {
            fw.write(LocalDateTime.now() + ">> (Deposit) ");
            fw.write("Plus(+) " + money + "\n");
            fw.write("current balance:" + Account.getBalance() + "\n");
        }catch (IOException e){
            System.out.println("writeDepositLog error!!");
        }
    }
    public void writeWithdrawLog(int money){
        try (FileWriter fw = new FileWriter(logFilePath, true)) {
            fw.write(LocalDateTime.now() + ">> (Withdraw) ");
            fw.write("Minus(-) " + money + "\n");
            fw.write("current balance:" + Account.getBalance() + "\n");
        }catch (IOException e){
            System.out.println("writeWithdrawLog error!!");
        }
    }
    public void writePurchaseLog() {
        try (FileWriter fw = new FileWriter(logFilePath, true)) {
            fw.write(LocalDateTime.now() + ">> (Purchase) ");
            fw.write("product:" + name + " ,price:" + price + " ,quantity:" + quantity + "\n");
        }catch (IOException e){
            System.out.println("writePurchaseLog error!!");
        }

    }

    public void resetLog() throws IOException {
        FileWriter fw = new FileWriter(logFilePath);
        fw.write("");
        fw.close();
    }

    public void reset() {

        try (FileWriter fw = new FileWriter(receiptFilePath)) {
            fw.write("");
            resetLog();
            Account.resetBalance();
        } catch (IOException e) {
            System.out.println("Reset error!!");
        } catch (Exception e){
            System.out.println("Unknown error!!");
        }

        System.out.println("reset...");

    }

    public void updateValues() {
        String line;
        String lastLine = "";

        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
            if (br.readLine() == null) {
                System.out.println("File is empty");
                //fw.write(String.format("%-20s%10s%10s%10s\n\n", "Product", "Quantity", "Price", "PriceSum"));

                return;
            }

            while ((line = br.readLine()) != null)
                lastLine = line;
        } catch (FileNotFoundException e) {
            System.out.println("File reader error!!");
            return;
        } catch (IOException e) {
            System.out.println("initValue error!!");
            return;
        }

        StringTokenizer st = new StringTokenizer(lastLine, ":");
        st.nextToken();
        Account.setBalance(Integer.parseInt(st.nextToken()));
        System.out.println("balance:" + Account.getBalance());
        System.out.println("Init Complete...");
    }
}
