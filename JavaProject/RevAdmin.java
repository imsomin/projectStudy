import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

// ② 상품 재고관리 클래스
class RevAdmin extends Admin
{
    private static Scanner sc = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String con;
    //Inventory item = new Inventory();

    // 메뉴 선택
    void select() throws IOException
    {
        do {
            System.out.println("\n<< 수익 관리 현황 >>");
            System.out.println("1. 수익 확인");
            System.out.println("2. 수익 회수");
            System.out.println("3. 관리자 메뉴로 이동");

            do {
                System.out.print(">> ");
                con = br.readLine();

                if (Integer.parseInt(con) < 1 && Integer.parseInt(con) > 3)
                    System.out.println("잘못 입력하셨습니다.");
            }
            while (Integer.parseInt(con) < 1 && Integer.parseInt(con) > 3);

            switch (con) {
                case "1":
                    show();
                    break;    // 수익 확인
                case "2":
                    delete();
                    break;    // 수익 회수
            }
        }
        while (!con.equals("3"));
    }

    // 재고 보기 → 인터넷 참고
    @Override
    public void show() {
        int rev = re.getMoney();

        System.out.println("\n\n<<전체 수익 현황 조회>>\n");
        System.out.println("현재 수익은 " + rev + "원 입니다.");
    }

    // 재고 관리
    @Override
    public void add() {
    }

    // 재고 감소
    @Override
    public void delete() {
        System.out.println("\n[수익 회수]");
        try {
            System.out.println("회수할 금액을 입력하세요. ex) 10000");
            System.out.print(">> ");
            int subMoney = sc.nextInt();
            int result = re.removeMoney(subMoney);
            if(result != 1)
            {
                System.out.println("\n수익보다 회수할 금액이 큽니다.");
                delete();
            }
            else
            {
                System.out.println("\n회수 완료되었습니다.");
            }
        } catch (Exception e) {
            System.out.println("입력 형식이 틀립니다.");
            sc.nextLine();
        }
    }

    @Override
    public void onOff() {
        // 내용 없음
    }
}
