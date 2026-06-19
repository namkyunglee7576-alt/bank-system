package service;


import model.Account;
import repository.AccountRepository;
import java.util.List;

/**
 * 계좌 관련 비즈니스 로직을 처리하는 서비스 클래스
 * 담당: 팀원B
 */
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * 계좌 개설
     * 커밋1: 계좌개설 구현
     */
    public Account openAccount(String ownerName, long initialBalance) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("고객명을 입력해주세요.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("초기 잔액은 0원 이상이어야 합니다.");
        }
        return accountRepository.save(ownerName, initialBalance);
    }

    /**
     * 전체 계좌 조회
     */
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * 단건 조회
     */
    public Account findAccount(String accountNumber) {
        Account account = accountRepository.findById(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("존재하지 않는 계좌번호입니다: " + accountNumber);
        }
        return account;
    }

    /**
     * 입금
     * 커밋2: 입출금 구현
     */
    public void deposit(String accountNumber, long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("입금액은 0원보다 커야 합니다.");
        }
        Account account = findAccount(accountNumber);
        account.deposit(amount);
    }

    /**
     * 출금 (잔액 부족 시 예외처리)
     * 커밋2: 입출금 구현
     */
    public void withdraw(String accountNumber, long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("출금액은 0원보다 커야 합니다.");
        }
        Account account = findAccount(accountNumber);
        if (account.getBalance() < amount) {
            throw new IllegalStateException("잔액이 부족합니다. 현재 잔액: " + account.getBalance() + "원");
        }
        account.withdraw(amount);
    }

    /**
     * 이체 (출금 계좌 -> 입금 계좌)
     * 커밋3: 이체/해지 구현
     */
    public void transfer(String fromAccountNumber, String toAccountNumber, long amount) {
        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new IllegalArgumentException("동일한 계좌로는 이체할 수 없습니다.");
        }
        // 입금 대상 계좌가 존재하는지 먼저 검증
        findAccount(toAccountNumber);

        withdraw(fromAccountNumber, amount);
        deposit(toAccountNumber, amount);
    }

    /**
     * 계좌 해지 (잔액 0 확인 후 삭제)
     * 커밋3: 이체/해지 구현
     */
    public void closeAccount(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account.getBalance() != 0) {
            throw new IllegalStateException("잔액이 0원이 아니므로 해지할 수 없습니다. 현재 잔액: " + account.getBalance() + "원");
        }
        accountRepository.delete(accountNumber);
    }
}

