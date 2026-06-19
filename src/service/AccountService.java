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
    
//    전체 계좌 조회
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
}
