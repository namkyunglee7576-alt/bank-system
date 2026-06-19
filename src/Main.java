
import controller.AccountController;
import repository.AccountRepository;
import service.AccountService;
import view.AccountView;

public class Main {

    public static void main(String[] args) {
        printStartBanner();

        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        AccountView accountView = new AccountView();
        AccountController accountController = new AccountController(accountService, accountView);

        setupSampleData(accountService);

        accountController.run();

        printExitMessage();
    }

    private static void printStartBanner() {
        System.out.println("******************************************");
        System.out.println("*   신한DS금융소프트웨어 아카데미 7기      *");
        System.out.println("*        은행 계좌 관리 시스템          *");
        System.out.println("******************************************");
    }

    private static void setupSampleData(AccountService accountService) {
        accountService.openAccount("홍길동", 100000);
        accountService.openAccount("김신한", 50000);
        System.out.println(">> 샘플 데이터가 등록되었습니다.");
    }

    private static void printExitMessage() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println(" 이용해주셔서 감사합니다. 다음에 또 만나요!");
        System.out.println("==========================================");
    }
}
