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
    
}
