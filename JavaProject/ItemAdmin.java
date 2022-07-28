import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

// ② 상품 재고관리 클래스
class ItemAdmin extends Admin
{
    private static Scanner sc = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String con;
    //Inventory item = new Inventory();

    // 메뉴 선택
    void select() throws IOException
    {
        do {
            System.out.println("\n<< 상품 관리 현황 >>");
            System.out.println("1. 상품 재고 확인");
            System.out.println("2. 상품 재고 추가");
            System.out.println("3. 상품 재고 삭제");
            System.out.println("4. 관리자 메뉴로 이동");

            do {
                System.out.print(">> ");
                con = br.readLine();

                if (Integer.parseInt(con) < 1 && Integer.parseInt(con) > 4)
                    System.out.println("잘못 입력하셨습니다.");
            }
            while (Integer.parseInt(con) < 1 && Integer.parseInt(con) > 4);

            switch (con) {
                case "1":
                    show();
                    break;    // 재고 확인
                case "2":
                    add();
                    break;    // 재고 추가
                case "3":
                    delete();
                    break;    // 재고 삭제
            }
        }
        while (!con.equals("4"));
    }

    // 재고 보기 → 인터넷 참고
    @Override
    public void show() {
        String items = iv.getInvens();

        System.out.println("\n\n<<전체 상품 현황 조회>>\n");
        System.out.println(items);
    }

    // 재고 관리
    @Override
    public void add() {

        System.out.println("\n< 상품 추가 >");
        try {
            System.out.println("상품 이름과 수량을 입력하세요 ex) 일회용 소형 마스크,2");
            String nameNum = sc.nextLine();
            String[] str = nameNum.split(",");
            iv.addInvNum(str[0],Integer.parseInt(str[1]));
            System.out.println("추가 완료되었습니다.");

        } catch (InputMismatchException e) {
            System.out.println("입력 형식이 틀립니다.");
            sc.nextLine();
        }
    }

    // 재고 감소
    @Override
    public void delete() {
        System.out.println("\n[상품의 재고 관리(삭제)]");
        try {
            System.out.println("제거할 상품 이름과 수량을 입력하세요. ex) 일회용 중형 마스크,3");
            String nameNum = sc.nextLine();
            String[] str = nameNum.split(",");
            iv.sellItems(str[0],Integer.parseInt(str[1]));
            System.out.println("제거 완료되었습니다.");
        } catch (InputMismatchException e) {
            System.out.println("입력 형식이 틀립니다.");
            sc.nextLine();
        }
    }

    @Override
    public void onOff() {
        // 내용 없음
    }
}
