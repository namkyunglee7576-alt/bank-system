package controller;

import model.Account;
import service.AccountService;
import view.AccountView;
import java.util.List;

/**
 * 메뉴 흐름을 제어하는 컨트롤러 클래스
 * 담당: 팀원C
 */
public class AccountController {

    private AccountService accountService;
    private AccountView accountView;

    public AccountController(AccountService accountService, AccountView accountView) {
        this.accountService = accountService;
        this.accountView = accountView;
    }

    /**
     * 메뉴 루프
     * 커밋1: Controller 메뉴 루프
     */
    public void run() {
        boolean running = true;
        while (running) {
            accountView.printMenu();
            try {
                int menu = accountView.inputMenu();
                switch (menu) {
                    case 1:
                        openAccount();
                        break;
                    case 2:
                        findAllAccounts();
                        break;
                    case 3:
                        findAccount();
                        break;
                    case 4:
                        deposit();
                        break;
                    case 5:
                        withdraw();
                        break;
                    case 6:
                        transfer();
                        break;
                    case 7:
                        closeAccount();
                        break;
                    case 0:
                        running = false;
                        accountView.printMessage("프로그램을 종료합니다.");
                        break;
                    default:
                        accountView.printError("올바른 메뉴 번호를 입력하세요.");
                }
            } catch (NumberFormatException e) {
                accountView.printError("숫자만 입력 가능합니다.");
            } catch (IllegalArgumentException | IllegalStateException e) {
                accountView.printError(e.getMessage());
            }
        }
    }

    /**
     * 각 기능 처리
     * 커밋2: Controller 각 기능 처리
     */
    private void openAccount() {
        String ownerName = accountView.inputOwnerName();
        long initialBalance = accountView.inputInitialBalance();
        Account account = accountService.openAccount(ownerName, initialBalance);
        accountView.printMessage("계좌가 개설되었습니다.");
        accountView.printAccount(account);
    }

    private void findAllAccounts() {
        List<Account> accounts = accountService.findAllAccounts();
        accountView.printAccountList(accounts);
    }

    private void findAccount() {
        String accountNumber = accountView.inputAccountNumber();
        Account account = accountService.findAccount(accountNumber);
        accountView.printAccount(account);
    }

    private void deposit() {
        String accountNumber = accountView.inputAccountNumber();
        long amount = accountView.inputAmount();
        accountService.deposit(accountNumber, amount);
        accountView.printMessage("입금이 완료되었습니다.");
        accountView.printAccount(accountService.findAccount(accountNumber));
    }

    private void withdraw() {
        String accountNumber = accountView.inputAccountNumber();
        long amount = accountView.inputAmount();
        accountService.withdraw(accountNumber, amount);
        accountView.printMessage("출금이 완료되었습니다.");
        accountView.printAccount(accountService.findAccount(accountNumber));
    }

    private void transfer() {
        String fromAccountNumber = accountView.inputAccountNumber("출금");
        String toAccountNumber = accountView.inputAccountNumber("입금");
        long amount = accountView.inputAmount();
        accountService.transfer(fromAccountNumber, toAccountNumber, amount);
        accountView.printMessage("이체가 완료되었습니다.");
        accountView.printAccount(accountService.findAccount(fromAccountNumber));
        accountView.printAccount(accountService.findAccount(toAccountNumber));
    }

    private void closeAccount() {
        String accountNumber = accountView.inputAccountNumber();
        accountService.closeAccount(accountNumber);
        accountView.printMessage("계좌가 해지되었습니다.");
    }
}
