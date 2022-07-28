import java.io.IOException;
import java.util.Scanner;

class BuyMask extends User
{
    Scanner sc = new Scanner(System.in);

    // BuyMaskRun 메소드
    public void buyMaskRun() throws IOException
    {
        buyMask();
        goMenu();
    }

    // 마스크 종류 선택 안내 메소드
    public int printMask()
    {
        int num = 0;
        do
        {
            System.out.println("\n[1] 일회용 마스크");
            System.out.println("[2] KF80 마스크");
            System.out.println("[3] KF94 마스크");
            System.out.print(">> 메뉴선택(1~3) : ");
            num = sc.nextInt();
        }
        while (num < 1 || num > 3);
        return num;
    }

    // 마스크 크기 선택 안내 메소드
    public int printMaskSize()
    {
        int num = 0;
        do
        {
            System.out.println("\n[마스크 크기 선택]");
            System.out.println("[1] 소형: 1000원");
            System.out.println("[2] 중형: 2000원");
            System.out.println("[3] 대형: 3000원");
            System.out.print(">> 메뉴선택(1~3) : ");
            num = sc.nextInt();
        }
        while (num < 1 || num > 3);
        return num;
    }

    // 장바구니 추가 or 구매하기 안내 메소드()
    public int printCartBuy()
    {
        int num = 0;
        do
        {
            System.out.println("\n[1] 장바구니에 추가");
            System.out.println("[2] 구매하기");
            System.out.print(">> 메뉴선택(1~2) : ");
            num = sc.nextInt();
        }
        while (num < 1 || num > 2);
        return num;
    }

    // 마스크 구매 기능 메소드
    public void buyMask() throws IOException
    {
        Cart ca = new Cart();

        int num1 = printMask(); // 어떤 종류의 마스크를 구매할 것인지
        if(num1 == 1) // 일회용 마스크 선택
        {
            int num2 = printMaskSize();

            if (num2 == 1) // 소형 일회용 마스크 선택
            {
                int num3 = printCartBuy(); // 장바구니 or 구매하기 선택 결과
                if(num3 == 1)
                {
                    addCart("일회용 소형 마스크", 1000);
                }
                else if (num3 == 2)
                {
                    addCart("일회용 소형 마스크", 1000);
                    buy();
                }
            } //num2 소형 일회용마스크
            else if (num2 == 2) // 중형 일회용 마스크 선택
            {
                int num3 = printCartBuy(); // 장바구니 or 구매하기 선택 결과
                if(num3 == 1)
                {
                    addCart("일회용 중형 마스크", 2000);
                }
                else if (num3 == 2)
                {
                    addCart("일회용 중형 마스크", 2000);
                    buy();
                }
            }//num2 중형 일회용마스크
            else if(num2 == 3) // 대형 일회용 마스크 선택
            {
                int num3 = printCartBuy(); // 장바구니 or 구매하기 선택 결과
                if(num3 == 1)
                {
                    addCart("일회용 대형 마스크", 3000);
                }
                else if (num3 == 2)
                {
                    addCart("일회용 대형 마스크", 3000);
                    buy();
                }
            }//num2 대형 일회용마스크
        }//num1 일회용 마스크 끝

        else if(num1==2) //KF80 마스크 선택 시
        {
            int num2 = printMaskSize(); // 마스크 크기 선택
            if (num2 == 1) // KF80 소형 마스크 선택
            {
                int num3 = printCartBuy(); // 장바구니 or 구매하기 선택 결과
                if(num3 == 1)
                {
                    addCart("KF80 소형 마스크", 1000);
                }
                else if (num3 == 2)
                {
                    addCart("KF80 소형 마스크", 1000);
                    buy();
                }
            }//num2 kf80 소형
            else if (num2 == 2)
            {
                int num3 = printCartBuy(); // 장바구니 or 구매하기 선택 결과
                if(num3 == 1)
                {
                    addCart("KF80 중형 마스크", 2000);
                }
                else if (num3 == 2)
                {
                    addCart("KF80 중형 마스크", 2000);
                    buy();
                }
            }//num2 kf80 중형
            else if(num2 == 3)
            {
                int num3 = printCartBuy(); // 장바구니 or 구매하기 선택 결과
                if(num3 == 1)
                {
                    addCart("KF80 대형 마스크", 3000);
                }
                else if (num3 == 2)
                {
                    addCart("KF80 대형 마스크", 3000);
                    buy();
                }
            }//num2 kf80 대형
        }//num1 kf80 끝
        else if(num1 == 3)
        {
            int num2 = printMaskSize(); // 마스크 크기 선택
            if (num2 == 1) // KF94 소형 마스크 선택
            {
                int num3 = printCartBuy(); // 장바구니 or 구매하기 선택 결과
                if(num3 == 1)
                {
                    addCart("KF94 소형 마스크", 1000);
                }
                else if (num3 == 2)
                {
                    addCart("KF94 소형 마스크", 1000);
                    buy();
                }
            }//num2 KF94 소형
            else if (num2 == 2)
            {
                int num3 = printCartBuy(); // 장바구니 or 구매하기 선택 결과
                if(num3 == 1)
                {
                    addCart("KF94 중형 마스크", 2000);
                }
                else if (num3 == 2)
                {
                    addCart("KF94 중형 마스크", 2000);
                    buy();
                }
            }//num2 KF94 중형
            else if(num2 == 3)
            {
                int num3 = printCartBuy(); // 장바구니 or 구매하기 선택 결과
                if(num3 == 1)
                {
                    addCart("KF94 대형 마스크", 3000);
                }
                else if (num3 == 2)
                {
                    addCart("KF94 대형 마스크", 3000);
                    buy();
                }
            }//num2 KF94 대형
        }//num1 kf94 끝
    }
}