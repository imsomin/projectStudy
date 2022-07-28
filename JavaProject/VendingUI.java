import java.io.IOException;
import java.util.Scanner;


class VendingUI extends User
{
    private static int sel;
    private static BuyMask bm = new BuyMask();
    private static BuyHand bh = new BuyHand();
    //private static Cart ca = new Cart();
    private static User us = new User();
    private static KitTest kt = new KitTest();
    private static AdminUI ai = new AdminUI();

    //메뉴 출력 메소드
    public static void printMenu()
    {
        System.out.println("\n[메뉴 선택]");
        System.out.println("[1] 마스크 구매 ");
        System.out.println("[2] 손소독제 구매 ");
        System.out.println("[3] 자가진단키트 구매 ");
        System.out.println("[4] 장바구니 확인 ");
        System.out.println("[5] 구매하기 ");
        System.out.println("[6] 관리자 모드 ");
        System.out.print(">> 메뉴선택 (1~6) : ");
    }

    //메뉴 선택 메소드
    public static void menuSelect()
    {
        //Scanner 인스턴스 생성
        Scanner sc = new Scanner(System.in);

        do
        {
            sel = sc.nextInt();
        }
        while (sel < 1 || sel > 6);
    }

    // 메뉴로 이동하는 메소드
    public static void menuRun() throws IOException
    {
        switch (sel)
        {
            case 1: bm.buyMaskRun(); break;
            case 2: bh.buyHandRun(); break;
            case 3: kt.KitRun(); break;
            case 4: ca.seeCart(); goMenu(); break;
            case 5: us.buy(); break;
            case 6: ai.onSystem(); break;
        }
    }
}