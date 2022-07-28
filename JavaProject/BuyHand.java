import java.io.IOException;
import java.util.Scanner;

class BuyHand extends User
{
    Scanner sc = new Scanner(System.in);

    public void buyHandRun() throws IOException
    {
        buyHand();
        goMenu();
    }

    public int printHand()
    {
        int hand;
        do
        {
            System.out.println("\n[1] 포켓형 손소독제   : 1000원");
            System.out.println("[2] 살균형 손소독제   : 2000원");
            System.out.println("[3] 핸드펌프 손소독제 : 3000원");
            System.out.print(">> 메뉴선택 (1~3) : ");
            hand = sc.nextInt();
        }
        while (hand < 1 || hand > 3);
        return hand;
    }

    // 장바구니 추가 or 구매하기 메소드()
    public int printCartBuy()
    {
        int num;
        do
        {
            System.out.println("\n[1] 장바구니에 추가");
            System.out.println("[2] 구매하기");
            System.out.print(">> 메뉴선택 (1~2) : ");
            num = sc.nextInt();
        }
        while (num < 1 || num > 2);
        return num;
    }

    public void buyHand() throws IOException
    {
        int hand = printHand();
        if(hand == 1) // 포켓형 손소독제
        {
            int num = printCartBuy();
            if(num == 1) // 장바구니에 추가
            {
                addCart("포켓형 손소독제", 1000);
            }
            else if(num == 2) // 구매하기
            {
                addCart("포켓형 손소독제", 1000);
                buy();
            }
        }
        else if(hand == 2)
        {
            int num = printCartBuy();
            if(num == 1) // 장바구니에 추가
            {
                addCart("살균형 손소독제", 2000);
            }
            else if(num == 2) // 구매하기
            {
                addCart("살균형 손소독제", 2000);
                buy();
            }
        }
        else if(hand == 3)
        {
            int num = printCartBuy();
            if(num == 1) // 장바구니에 추가
            {
                addCart("핸드펌프 손소독제", 3000);
            }
            else if(num == 2) // 구매하기
            {
                addCart("핸드펌프 손소독제", 3000);
                buy();
            }
        }
    }
}