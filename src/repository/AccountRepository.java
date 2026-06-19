package repository;

import model.Account;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private List<Account> accounts = new ArrayList<>();
    private int sequence = 1000; // 계좌번호 자동 발급용 시퀀스

    /**
     * 계좌 저장
     */
    public Account save(String ownerName, long initialBalance) {
        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, ownerName, initialBalance);
        accounts.add(account);
        return account;
    }

    /**
     * 전체 계좌 목록 조회
     */
    public List<Account> findAll() {
        return accounts;
    }

    /**
     * 계좌번호로 단건 조회
     */
    public Account findById(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    /**
     * 계좌 삭제 (해지)
     */
    public boolean delete(String accountNumber) {
        Account target = findById(accountNumber);
        if (target == null) {
            return false;
        }
        accounts.remove(target);
        return true;
    }

    /**
     * 계좌번호 자동 생성 (예: ACC-1001)
     */
    private String generateAccountNumber() {
        sequence++;
        return "ACC-" + sequence;
    }
}
