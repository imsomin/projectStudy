import java.io.IOException;
import java.util.Scanner;

class User extends Top  // 모든 클래스가 상속 받을 부모 클래스
{

    String cartCheck;

    // 각종 변수 선언 및 정의
    int money = 0; // 사용자가 넣은 총 금액 (리셋되면 안되므로)

    public void buy() throws IOException //구매하기 메소드
    {
        Scanner sc = new Scanner(System.in);
        int price = ca.getPrice(); // 장바구니에 담은 물건의 총 금액
        int unit = 0; // 넣는 지폐(동전)의 단위
        int number = 0; // 지폐(동전) 개수
        int changes; // 잔돈 변수
        System.out.println("장바구니에 담긴 물건의 총 금액은 " + price + "원 입니다.");

        do
        {
            System.out.println("\n얼마짜리 지폐(동전)를 넣으십니까?");
            System.out.println("[1] 10000원권");
            System.out.println("[2] 5000원권");
            System.out.println("[3] 1000원권");
            System.out.println("[4] 500원");
            System.out.println("[5] 100원");
            System.out.println("[6] 계산하기");
            System.out.println("현재 넣은 금액은 " + money + "원 입니다.");
            System.out.print(">> 메뉴 선택 (1~6) : ");
            unit = sc.nextInt(); // 지폐, 동전 단위 입력
            if(unit <= 5 && unit >= 1)
            {
                System.out.print(">> 몇 개 넣으십니까? : ");
                number = sc.nextInt(); // 넣을 개수
                switch (unit)
                {
                    case 1: money += 10000 * number; break;
                    case 2: money += 5000 * number; break;
                    case 3: money += 1000 * number; break;
                    case 4: money += 500 * number; break;
                    case 5: money += 100 * number; break;
                }
                unit = -1;
            }
        }
        while ((unit > 5 || unit < 1) && unit != 6);


        if (price > money)
        {
            System.out.println("넣은 금액이 모자랍니다.\n");
            buy(); // 재귀함수
        }
        else
        {
            changes = money - price;
            re.addMoney(price); // 수익에 판 금액 추가하기

            // 구매한 물건 재고에 반영하기
            String cartItems = ca.getCartName();
            String[] str = cartItems.split(",");

            for (String a : str)
            {
                iv.sellItems(a, ca.getItemNum(a));
            }

            pb.print(money, changes);

            // 장바구니 비우기
            ca.deleteAll();
            ca.deletePrice();

        }
        goMenu();

    }

    public void openCart() // 카트 확인 메소드
    {
        cartCheck = ca.getCartItems();
        System.out.println(cartCheck);
    }

    public static void goMenu() throws IOException // 초기 메뉴 메소드
    {
        VendingUI.printMenu();
        VendingUI.menuSelect();
        VendingUI.menuRun();
    }

    public void addCart(String name ,int price) throws IOException  // 장바구니 추가 메소드
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n수량 입력 : ");
        int n = sc.nextInt();
        ca.addItems(name, n);
        ca.addPrice(price * n);
        System.out.println("\n장바구니 : " + ca.getCartItems());
    }
}