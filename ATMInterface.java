import java.util.*;

class BankAccount {
    String userId;
    String userPin;
    double balance;
    List<String> transactionHistory;

    // Constructor
    BankAccount(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    // Deposit money
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("✅ Successfully deposited ₹" + amount);
        } else {
            System.out.println("❌ Invalid deposit amount!");
        }
    }

    // Withdraw money
    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount);
            System.out.println("✅ Successfully withdrew ₹" + amount);
        } else {
            System.out.println("❌ Insufficient balance or invalid amount!");
        }
    }

    // Transfer money
    void transfer(double amount, BankAccount receiver) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            receiver.balance += amount;
            transactionHistory.add("Transferred ₹" + amount + " to User ID: " + receiver.userId);
            System.out.println("✅ Successfully transferred ₹" + amount + " to " + receiver.userId);
        } else {
            System.out.println("❌ Insufficient balance or invalid transfer amount!");
        }
    }

    // Show transaction history
    void showTransactionHistory() {
        System.out.println("\n===== Transaction History =====");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet!");
        } else {
            for (String record : transactionHistory) {
                System.out.println(record);
            }
        }
        System.out.println("Current Balance: ₹" + balance);
    }
}

public class ATMInterface {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Create sample user accounts
        BankAccount user1 = new BankAccount("user1", "1234");
        BankAccount user2 = new BankAccount("user2", "5678");

        System.out.println("===== Welcome to ATM Interface =====");
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();
        System.out.print("Enter User PIN: ");
        String userPin = sc.nextLine();

        BankAccount currentUser = null;

        // Authentication
        if (userId.equals(user1.userId) && userPin.equals(user1.userPin)) {
            currentUser = user1;
        } else if (userId.equals(user2.userId) && userPin.equals(user2.userPin)) {
            currentUser = user2;
        }

        if (currentUser != null) {
            System.out.println("\n✅ Login Successful!");
            showMenu(currentUser, user1, user2);
        } else {
            System.out.println("❌ Invalid User ID or PIN!");
        }
    }

    // Menu after login
    static void showMenu(BankAccount currentUser, BankAccount user1, BankAccount user2) {
        int choice;
        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    currentUser.showTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmt = sc.nextDouble();
                    currentUser.withdraw(withdrawAmt);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmt = sc.nextDouble();
                    currentUser.deposit(depositAmt);
                    break;
                case 4:
                    sc.nextLine(); // consume newline
                    System.out.print("Enter receiver User ID: ");
                    String receiverId = sc.nextLine();
                    BankAccount receiver = receiverId.equals(user1.userId) ? user1 : user2;
                    if (receiver != null && receiver != currentUser) {
                        System.out.print("Enter amount to transfer: ₹");
                        double transferAmt = sc.nextDouble();
                        currentUser.transfer(transferAmt, receiver);
                    } else {
                        System.out.println("❌ Invalid receiver!");
                    }
                    break;
                case 5:
                    System.out.println("👋 Thank you for using ATuserM Interface!");
                    break;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        } while (choice != 5);
    }
}

