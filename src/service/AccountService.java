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
    
    // 단건조회
    public Account findAccount(String accountNumber) {
        Account account = accountRepository.findById(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("존재하지 않는 계좌번호입니다: " + accountNumber);
        }
        return account;
    }

    
    /**
     * 계좌 개설
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
     * 출금 (잔액 부족 시 예외처리)
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
    
    
	private Account findAccount(String accountNumber) {
			// TODO Auto-generated method stub
			return null;
		} 

	//    전체 계좌 조회
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
}
