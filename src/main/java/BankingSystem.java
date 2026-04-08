import java.util.*;
class BankAccount {
    int accountNumber;
    String username;
    double balance;
    public BankAccount(int accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
    public void display() {
        System.out.println(accountNumber + ". " + username + " – Balance: " + balance);
    }
}
public class BankingSystem {
    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> history = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Queue<BankAccount> accountRequests = new LinkedList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1 – Enter Bank");
            System.out.println("2 – Enter ATM");
            System.out.println("3 – Admin Area");
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
                default -> System.out.println("Invalid choice");
            }
        }
    }


    static void addAccount() {
        System.out.print("Enter account number: ");
        int id = sc.nextInt();
        System.out.print("Enter username: ");
        String name = sc.next();
        System.out.print("Enter balance: ");
        double balance = sc.nextDouble();
        accounts.add(new BankAccount(id, name, balance));
        System.out.println("Account added successfully");
    }
    static void displayAccounts() {
        System.out.println("\nAccounts List:");
        for (BankAccount acc : accounts) {
            acc.display();
        }
    }
    static BankAccount findAccount(String username) {
        for (BankAccount acc : accounts) {
            if (acc.username.equals(username)) {
                return acc;
            }
        }
        return null;
    }


    static void deposit() {
        System.out.print("Enter username: ");
        String name = sc.next();
        BankAccount acc = findAccount(name);
        if (acc != null) {
            System.out.print("Deposit: ");
            double amount = sc.nextDouble();
            acc.balance += amount;
            history.push("Deposit " + amount + " to " + name);
            System.out.println("New balance: " + acc.balance);
        } else {
            System.out.println("Account not found");
        }
    }
    static void withdraw() {
        System.out.print("Enter username: ");
        String name = sc.next();
        BankAccount acc = findAccount(name);
        if (acc != null) {
            System.out.print("Withdraw: ");
            double amount = sc.nextDouble();
            if (acc.balance >= amount) {
                acc.balance -= amount;
                history.push("Withdraw " + amount + " from " + name);
                System.out.println("New balance: " + acc.balance);
            } else {
                System.out.println("Insufficient funds");
            }
        } else {
            System.out.println("Account not found");
        }
    }


    static void showLastTransaction() {
        if (!history.isEmpty()) {
            System.out.println("Last transaction: " + history.peek());
        } else {
            System.out.println("No history");
        }
    }
    static void undoTransaction() {
        if (!history.isEmpty()) {
            System.out.println("Undo → " + history.pop());
        } else {
            System.out.println("Nothing to undo");
        }
    }


    static void addBill() {
        sc.nextLine();
        System.out.print("Enter bill name: ");
        String bill = sc.nextLine();
        billQueue.add(bill);
        System.out.println("Added: " + bill);
    }
    static void processBill() {
        if (!billQueue.isEmpty()) {
            System.out.println("Processing: " + billQueue.poll());
        } else {
            System.out.println("No bills");
        }
    }
    static void showBills() {
        System.out.println("Bills Queue: " + billQueue);
    }


    static void requestAccount() {
        System.out.print("Enter username: ");
        String name = sc.next();
        accountRequests.add(new BankAccount(0, name, 0));
        System.out.println("Request submitted");
    }
    static void processRequest() {
        if (!accountRequests.isEmpty()) {
            BankAccount acc = accountRequests.poll();
            acc.accountNumber = accounts.size() + 1;
            accounts.add(acc);
            System.out.println("Account created for " + acc.username);
        } else {
            System.out.println("No requests");
        }
    }
    static void showRequests() {
        System.out.println("Pending requests:");
        for (BankAccount acc : accountRequests) {
            System.out.println(acc.username);
        }
    }


    static void arrayExample() {
        BankAccount[] arr = new BankAccount[3];
        arr[0] = new BankAccount(1, "Ali", 150000);
        arr[1] = new BankAccount(2, "Sara", 220000);
        arr[2] = new BankAccount(3, "John", 300000);
        System.out.println("\nArray Accounts:");
        for (BankAccount acc : arr) {
            acc.display();
        }
    }


    static void bankMenu() {
        System.out.println("\n1 Add Account");
        System.out.println("2 Deposit");
        System.out.println("3 Withdraw");
        System.out.println("4 Request Account");
        System.out.println("5 Add Bill");
        System.out.println("6 Process Bill");
        System.out.println("7 Show Last Transaction");
        System.out.println("8 Undo Transaction");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> addAccount();
            case 2 -> deposit();
            case 3 -> withdraw();
            case 4 -> requestAccount();
            case 5 -> addBill();
            case 6 -> processBill();
            case 7 -> showLastTransaction();
            case 8 -> undoTransaction();
        }
    }
    static void atmMenu() {
        System.out.print("Enter username: ");
        String name = sc.next();
        BankAccount acc = findAccount(name);
        if (acc != null) {
            System.out.println("Balance: " + acc.balance);
            System.out.print("Withdraw: ");
            double amount = sc.nextDouble();
            if (acc.balance >= amount) {
                acc.balance -= amount;
                System.out.println("New balance: " + acc.balance);
            } else {
                System.out.println("Not enough money");
            }
        } else {
            System.out.println("Account not found");
        }
    }
    static void adminMenu() {
        System.out.println("\n1 View Requests");
        System.out.println("2 Process Request");
        System.out.println("3 View Bills");
        System.out.println("4 Show Accounts");
        System.out.println("5 Array Example");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> showRequests();
            case 2 -> processRequest();
            case 3 -> showBills();
            case 4 -> displayAccounts();
            case 5 -> arrayExample();
        }
    }
}