import java.io.*;
import java.util.*;

class Account implements Serializable {
    private String accountNumber;
    private String holderName;
    private double balance;

    public Account(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("₹" + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        }
    }

    public void display() {
        System.out.println("Account No: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: ₹" + balance);
        System.out.println("-----------------------------");
    }
}

public class BankingSystem {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        loadAccounts();
        int choice;
        do {
            System.out.println("\n=== Banking Management System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> depositMoney();
                case 3 -> withdrawMoney();
                case 4 -> checkBalance();
                case 5 -> viewAllAccounts();
                case 6 -> saveAccounts();
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    static void createAccount() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        System.out.print("Enter Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Deposit: ");
        double balance = sc.nextDouble();

        Account acc = new Account(accNo, name, balance);
        accounts.put(accNo, acc);
        System.out.println("Account created successfully!");
    }

    static void depositMoney() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        Account acc = accounts.get(accNo);
        if (acc != null) {
            System.out.print("Enter Amount to Deposit: ");
            double amt = sc.nextDouble();
            acc.deposit(amt);
        } else {
            System.out.println("Account not found!");
        }
    }

    static void withdrawMoney() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        Account acc = accounts.get(accNo);
        if (acc != null) {
            System.out.print("Enter Amount to Withdraw: ");
            double amt = sc.nextDouble();
            acc.withdraw(amt);
        } else {
            System.out.println("Account not found!");
        }
    }

    static void checkBalance() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        Account acc = accounts.get(accNo);
        if (acc != null) {
            acc.display();
        } else {
            System.out.println("Account not found!");
        }
    }

    static void viewAllAccounts() {
        for (Account acc : accounts.values()) {
            acc.display();
        }
    }

    static void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.txt"))) {
            oos.writeObject(accounts);
            System.out.println("All data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    static void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.txt"))) {
            accounts = (HashMap<String, Account>) ois.readObject();
            System.out.println("Accounts loaded successfully!");
        } catch (Exception e) {
            System.out.println("No previous records found. Starting fresh.");
        }
    }
}
