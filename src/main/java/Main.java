import java.util.Scanner;
public class Main {
    static MyLinkedList accounts = new MyLinkedList();
    static MyStack history = new MyStack();
    static MyQueue billQueue = new MyQueue();
    static MyQueue accountRequests = new MyQueue();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1 – Bank");
            System.out.println("2 – ATM");
            System.out.println("3 – Admin");
            System.out.println("4 – Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> bankMenu();
                case 2 -> atmMenu();
                case 3 -> adminMenu();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }
    static void bankMenu() {
        System.out.println("\n--- BANK MENU ---");
        System.out.println("1. Request account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1 -> {
                System.out.print("Enter username: ");
                String name = sc.nextLine();
                accountRequests.enqueue(name);
                System.out.println("Request submitted!");
            }
            case 2 -> {
                System.out.print("Enter username: ");
                String name = sc.nextLine();
                BankAccount acc = accounts.find(name);
                if (acc != null) {
                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    acc.deposit(amount);
                    System.out.println("Deposit successful!");
                    System.out.println("New balance: " + acc.balance);
                    history.push("Deposit " + amount + " to " + name);
                } else {
                    System.out.println("Account not found");
                }
            }
            case 3 -> {
                System.out.print("Enter username: ");
                String name = sc.nextLine();
                BankAccount acc = accounts.find(name);
                if (acc != null) {
                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    if (acc.withdraw(amount)) {
                        System.out.println("Withdraw successful!");
                        System.out.println("New balance: " + acc.balance);
                        history.push("Withdraw " + amount + " from " + name);
                    } else {
                        System.out.println("Not enough balance");
                    }
                } else {
                    System.out.println("Account not found");
                }
            }
        }
    }
    static void atmMenu() {
        sc.nextLine();
        System.out.print("Enter username: ");
        String name = sc.nextLine();
        BankAccount acc = accounts.find(name);
        if (acc != null) {
            System.out.println("Balance: " + acc.balance);
        } else {
            System.out.println("Account not found");
        }
    }
    static void adminMenu() {
        System.out.println("\n--- ADMIN MENU ---");
        System.out.println("1. Process account request");
        System.out.println("2. Bills");
        System.out.println("3. History");
        System.out.println("4. Show accounts");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1 -> {
                String requestName = accountRequests.dequeue();
                if (!requestName.equals("EMPTY")) {
                    BankAccount newAcc =
                            new BankAccount(accounts.size() + 1, requestName, 0);
                    accounts.add(newAcc);
                    System.out.println("Account created for " + requestName);
                } else {
                    System.out.println("No requests");
                }
            }
            case 2 -> {
                System.out.println("1. Add bill");
                System.out.println("2. Process bill");
                System.out.println("3. Show bills");
                int x = sc.nextInt();
                sc.nextLine();
                if (x == 1) {
                    String bill = sc.nextLine();
                    billQueue.enqueue(bill);
                }
                if (x == 2) {
                    System.out.println("Processing: " + billQueue.dequeue());
                }
                if (x == 3) {
                    billQueue.display();
                }
            }
            case 3 -> {
                System.out.println("Last: " + history.peek());
                System.out.println("Undo: " + history.pop());
            }
            case 4 -> accounts.displayAll();
        }
    }
}