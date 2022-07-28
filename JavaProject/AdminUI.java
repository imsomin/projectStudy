

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ① 로그인, UI, off 전원
class AdminUI
{
    private static ItemAdmin IA = new ItemAdmin();
    //BillAdmin BE = new ItemAdmin();
    private static CoinsAdmin CA = new CoinsAdmin();
    private static TextAdminRun TA = new TextAdminRun();
    private static RevAdmin RA = new RevAdmin();
    //VendingUI vu = new VendingUI();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private final String ADMIN_ID = "admin";
    private final String ADMIN_PW = "1234";

    private static int temp; //-- 사용자로부터 입력받은 값
    private static String sTemp;

    // 관리자 메뉴 실행
    void onSystem()
    {
        while(true) {
            try {
                if (!!login()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        while(true)
        {
            menuDisp();
            try {
                menuSelect();
                menuRun();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ⓑ메뉴 출력 메소드
    public void menuDisp()
    {
        System.out.println("\n[관리자 메뉴 선택]");
        System.out.println("1. 자판기 off");
        System.out.println("2. 상품의 재고 확인 및 관리");
        System.out.println("3. 수익 확인 및 회수 관리");
        System.out.println("4. 잔돈 확인 및 관리 ");
        System.out.println("5. 랜덤문구 관리");
        System.out.println("6. 초기 화면으로 돌아가기");
        System.out.print(">> 메뉴 선택(1~6) : ");
    }

    // ⓒ메뉴 선택 메소드
    public void menuSelect() throws IOException {
        do {
            System.out.print(">> ");
            temp = Integer.parseInt(br.readLine());

            if (!(temp >= 1 && temp <= 6)) {
                System.out.println("잘못 입력하셨습니다.");
            }
        }
        while (!(temp >= 1 && temp <= 6));
        // 1보다 크거나 6보다 작은 값이 '아닌게' 나올 때까지 반복
    }

    // ⓓ 선택된 메뉴 실행에 따른 기능 호출 메소드 → 완성
    public static void menuRun() throws IOException
    {


        switch (temp)
        {
            case 1 : runElement(); break;      //-- on/off
            case 2 : IA.select(); break;   //-- 상품 재고 관리 클래스
            case 3 : RA.select(); break;   //-- 영수증 용지 여부 확인 및 관리 클래스(+ 통합예정)
            case 4 : CA.select(); break;   //-- 잔돈 확인 및 관리 클래스
            case 5 : TA.textAdminRun();  break; //-- 랜덤 클래스(+ 통합예정)
            case 6 : exit(); break;         //-- 초기 화면으로 돌아가기 (+ 통합예정)
        }
    }

    // ⓐ로그인 메소드 → 홈과 연동
    //로그인 완료 시 관리자 페이지로 들어가는 법 → ?
    boolean login() throws IOException
    {
        System.out.println();
        System.out.println("<< 관리자 로그인 >>");

        // 아이디 확인
        do
        {
            System.out.print("◆ 관리자 아이디 : ");
            sTemp = br.readLine();

            if (!ADMIN_ID.equals(sTemp))
            {
                System.out.println("아이디를 잘못 입력하셨습니다.");
            }
        }
        while (!ADMIN_ID.equals(sTemp));

        // 비밀번호 확인
        do
        {
            System.out.print("◆ 관리자 비밀번호 : ");
            sTemp = br.readLine();

            if (!ADMIN_PW.equals(sTemp))
            {
                System.out.println("비밀번호를 잘못 입력하셨습니다.");
            }
        }
        while (!ADMIN_PW.equals(sTemp));

        // 로그인 성공
        System.out.println("관리자로 로그인하였습니다.");
        return true;
    }
    //메인 메뉴로 이동하는 메소드
    public static void exit() throws IOException
    {
        System.out.println("\n===홈메뉴 시작===");

        VendingUI.printMenu();
        VendingUI.menuSelect();
        VendingUI.menuRun();
    }
    // ⓓ 자판기 on/off
    public static void runElement()
    {
        // 디폴트 값을 on으로 유지, off로 진행하면 프로그램 종료되도록.
        System.out.println("\n자판기의 전원을 끕니다.");   // 전원을 끌 경우 프로그램 종료
        System.exit(-1);
    }
}