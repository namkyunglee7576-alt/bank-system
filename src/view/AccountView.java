package view;

import model.Account;
import java.util.List;
import java.util.Scanner;

/**
 * 콘솔 입출력을 담당하는 뷰 클래스
 */
public class AccountView {

    private Scanner scanner = new Scanner(System.in);

    /**
     * 메뉴 출력
     */
    public void printMenu() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("       신한DS 은행 계좌 관리 시스템");
        System.out.println("==========================================");
        System.out.println("1. 계좌 개설");
        System.out.println("2. 전체 계좌 조회");
        System.out.println("3. 단건 조회");
        System.out.println("4. 입금");
        System.out.println("5. 출금");
        System.out.println("6. 이체");
        System.out.println("7. 계좌 해지");
        System.out.println("0. 종료");
        System.out.println("==========================================");
        System.out.print("메뉴를 선택하세요 >> ");
    }

    public int inputMenu() {
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public String inputOwnerName() {
        System.out.print("고객명을 입력하세요 >> ");
        return scanner.nextLine().trim();
    }

    public long inputInitialBalance() {
        System.out.print("초기 잔액을 입력하세요 >> ");
        return Long.parseLong(scanner.nextLine().trim());
    }

    public String inputAccountNumber() {
        System.out.print("계좌번호를 입력하세요 >> ");
        return scanner.nextLine().trim();
    }

    public String inputAccountNumber(String label) {
        System.out.print(label + " 계좌번호를 입력하세요 >> ");
        return scanner.nextLine().trim();
    }

    public long inputAmount() {
        System.out.print("금액을 입력하세요 >> ");
        return Long.parseLong(scanner.nextLine().trim());
    }

    /**
     * 결과 출력 (성공 메시지)
     */
    public void printMessage(String message) {
        System.out.println(">> " + message);
    }

    /**
     * 에러 출력
     */
    public void printError(String message) {
        System.out.println("[오류] " + message);
    }

    /**
     * 단건 계좌 출력
     */
    public void printAccount(Account account) {
        System.out.println(account);
    }

    /**
     * 전체 계좌 목록 출력
     */
    public void printAccountList(List<Account> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("등록된 계좌가 없습니다.");
            return;
        }
        System.out.println("------------------------------------------");
        for (Account account : accounts) {
            System.out.println(account);
        }
        System.out.println("------------------------------------------");
        System.out.println("총 " + accounts.size() + "개 계좌");
    }
}
