public class BankAccount {
    int accountNumber;
    String username;
    double balance;
    public BankAccount(int accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
    public void deposit(double amount) {
        balance += amount;
    }
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    public void display() {
        System.out.println(accountNumber + ". " + username + " – Balance: " + balance);
    }
}