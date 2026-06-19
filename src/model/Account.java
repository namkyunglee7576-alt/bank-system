package model;

public class Account {

    private String accountNumber;  // 계좌번호
    private String ownerName;      // 예금주명
    private long balance;          // 잔액

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void deposit(long amount) {
        this.balance += amount;
    }

    public void withdraw(long amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "계좌번호: " + accountNumber +
                " | 예금주: " + ownerName +
                " | 잔액: " + balance + "원";
    }
}
