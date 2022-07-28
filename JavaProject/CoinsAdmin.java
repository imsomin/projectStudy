import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

// ③ 돈 재고관리 클래스
class CoinsAdmin extends Admin {
    private Scanner sc = new Scanner(System.in);
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String con;
    //Changes cha = new Changes();

    // 메뉴 선택
    void select() throws IOException {
        do {
            // 디스플레이
            System.out.println("\n<< 잔돈 관리 현황 >>");
            System.out.println("1. 잔돈 확인");
            System.out.println("2. 잔돈 재고 추가");
            System.out.println("3. 잔돈 재고 삭제");
            System.out.println("4. 관리자 메뉴로 이동");

            do {
                System.out.print(">> ");
                con = br.readLine();

                if (Integer.parseInt(con) < 1 && Integer.parseInt(con) > 4)
                    System.out.println("잘못 입력하셨습니다.");
            }
            while (Integer.parseInt(con) < 1 && Integer.parseInt(con) > 4);

            System.out.println();

            switch (con) {
                case "1":
                    show();
                    break;    // 잔돈 확인
                case "2":
                    add();
                    break;    // 잔돈 추가
                case "3":
                    delete();
                    break;    // 잔돈 삭제
            }
        }
        while (!con.equals("4"));
    }

    // 돈 재고 보기 → 인터넷 참고
    @Override
    public void show() {
        //Set<Map.Entry<String, Integer>> set = changesNum.entrySet();
        int[] MoneyName = {5000, 1000, 500, 100};
        int[] MoneyNumber = {ch.getChanges(5000),ch.getChanges(1000),ch.getChanges(500),ch.getChanges(100)};
        System.out.println("\n<<전체 잔돈 현황 조회>>");
        for (int i=0;i<MoneyName.length;i++)
        {
            System.out.printf("%d원 %d개",MoneyName[i],MoneyNumber[i]);
        }

    }

    // 재고 관리
    @Override
    public void add() {
        System.out.println("\n[잔돈 관리]");
        try
        {
            System.out.println("잔돈 단위와 수량을 입력하세요. ex) 5000 10 ");
            System.out.print(">> ");
            int name = sc.nextInt();
            int i = sc.nextInt();
            ch.addChanges(name,i);
        }
        catch (InputMismatchException e) {
            System.out.println("입력 형식이 틀립니다.");
            sc.nextLine();
        }
    }

    @Override
    public void delete() {
        System.out.println("\n[잔돈 관리(삭제)]");
        try {
            System.out.println("제거할 잔돈 단위와 수량을 입력하세요. ex) 1000 10");
            System.out.print(">> ");
            int name = sc.nextInt();
            int i = sc.nextInt();
            int result = ch.subChanges(name, i);
            if(result == 0)
            {
                delete();
            }

        } catch (InputMismatchException i) {
            System.out.println("입력 형식이 틀립니다.");
            sc.nextLine();
        }
    }

    @Override
    public void onOff() {
        // 내용 없음
    }
}