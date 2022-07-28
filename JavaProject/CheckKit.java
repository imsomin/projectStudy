import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Calendar;

import java.util.Scanner;


public class CheckKit extends User
{
    private int age;
    private int birthYear;                  // 태어난 해
    private int birthMonth;                 // 태어난 달
    private int birthDay;                   // 태어난 일
    Calendar now = Calendar.getInstance();

    int y = now.get(Calendar.YEAR);
    int m = now.get(Calendar.MONTH);
    int d = now.get(Calendar.DATE);

    boolean age60 = false;

    void dateOfBirth() throws IOException       // 생년월일 입력
    {
        Calendar now = Calendar.getInstance();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean loop = true;
        String str;
        do
        {
            System.out.print("\n생년월일을 입력하세요(8자리) : ");
            str = br.readLine();

            if(str.length() == 8)
            {
                birthYear = Integer.parseInt(str.substring(0, 4));
                birthMonth = Integer.parseInt(str.substring(4, 6));
                birthDay = Integer.parseInt(str.substring(6, 8));

                if(birthYear > 1800 && birthYear < now.get(Calendar.YEAR) + 1)
                {
                    if(birthMonth > 0 && birthMonth < 13)
                    {
                        int lastDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
                        if (birthDay > 0 && birthDay < lastDay)
                        {
                            loop = false;
                        }
                    }
                }
            }
        } while (loop);
    }

    void ageCalc()                          // 만나이 계산
    {
        age = y - birthYear;
        if(age == 60)               // 확인해보기
        {
            if(birthMonth > m+1)
                age = age - 1;
            else if(birthMonth == m+1 && birthDay > d)
                age = age -1;
        }

        if (age >= 60)
        {
            age60 = true;
        }
    }

    void checkSymptoms() throws IOException, NumberFormatException
    {       // 중복 체크 가능
        Scanner sc = new Scanner(System.in);
        boolean check = false;

        ageCalc();

        do
        {

            System.out.println("\n최근 3일 이내 발생한 증상을 입력해주세요.(중복 입력시 공백으로 구분) : ");
            System.out.println("[1] 무증상");
            System.out.println("[2] 기침");
            System.out.println("[3] 피로감");
            System.out.println("[4] 발열");
            System.out.println("[5] 호흡 곤란 또는 숨 가쁨");
            System.out.println("[6] 가슴 통증");
            System.out.println("[7] 미각 또는 후각 상실");
            System.out.print(">> 선택(1~7) : ");

            String st1 = sc.nextLine();
            String[] ans = st1.split("\\s");

            for (String st : ans)
            {
                int a = Integer.parseInt(st);

                if(a == 1 && ans.length == 1)
                {
                    textMessageCheck();
                }
                else if(a == 2 || a == 3 || a == 4 || a == 5 || a == 6 || a == 7)
                {
                    if (age60 == true)
                    {
                        informationPcr();
                    }
                    else if(age60 == false)
                    {
                        textMessageCheck();
                    }
                }
            }

        }while (check = true);
    }

    void textMessageCheck() throws IOException       //3번
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans;

        do
        {
            System.out.println("\n최근 7일 이내 밀접접촉등의 이유로 PCR 검사 요청을 받으신적이 있으십니까?");
            System.out.println("[1] 있다.");
            System.out.println("[2] 없다.");
            System.out.print(">> 선택(1~2) : ");
            ans = Integer.parseInt(br.readLine());

        }while (ans < 1 || ans > 2);

        switch (ans)
        {
            case 1:
                informationPcr();
                break;
            case 2:
                System.out.println("\n[2] 없다 를 선택하셨습니다. 다음 문항으로 넘어갑니다. ");
                checkDocNote();
        }
    }

    void checkDocNote() throws IOException       // 4번
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans;

        do
        {
            System.out.println("\n본인이 감염취약시설 종사자이거나 최근 3일이내 코로나19 검사가 필요하다는 의사 소견서를 받으신적이 있으십니까?");
            System.out.println("[1] 감염취약시설 종사자");
            System.out.println("[2] 코로나19 검사 필요하다는 의사 소견서 수령자");
            System.out.println("[3] 없다.");
            System.out.print(">> 선택(1~3) : ");
            ans = Integer.parseInt(br.readLine());
        } while (ans < 1 || ans > 3);

        switch (ans)
        {
            case 1:
            case 2:
                informationPcr();
                break;
            case 3:
                System.out.println("\n[3] 없다 를 선택하셨습니다. 다음 문항으로 넘어갑니다. ");
                positiveCheck();
                break;
        }
    }

    int positiveCheck() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int res;

        do
        {
            System.out.println("\n최근 7일 이내 병·의원(호흡기클리닉 등) 및 선별진료소에서 진행한 신속항원검사 결과 양성 결과가 나온 적이 있습니까?");
            System.out.println("[1] 있다.");
            System.out.println("[2] 없다.");
            System.out.print(">> 선택(1~2) : ");
            res = Integer.parseInt(br.readLine());
        }
        while (res < 1 || res > 2);

        switch (res)
        {
            case 1:
                System.out.println("자가진단키트를 구매하실 수 없습니다.  PCR 검사 대상자 안내 창으로 연결합니다.");
                informationPcr();
                break;
            case 2:
                System.out.println("\n[2] 없다 를 선택하셨습니다. 다음 문항으로 넘어갑니다. ");
                notPcr();
        }
        return res;
    }

    void notPcr() throws IOException
    {
        System.out.println("\nPCR 검사 우선순위 대상자가 아닙니다.");
        buyKit();
    }

    void informationPcr() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char res;

        System.out.println("\n가까운 코로나19 검사소에서 PCR 검사를 받으실 수 있습니다.");
        System.out.print("그래도 자가진단키트를 구매하시겠습니까?(Y/N) : ");

        res = (char)br.read();

        if(res == 'Y' || res == 'y')
        {
            buyKit();
        } else
        {
            goMenu();
        }
    }

    public int printCartBuy()
    {
        Scanner sc = new Scanner(System.in);

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

    public void buyKit() throws IOException
    {
        System.out.println("자가진단 키트 : 10000원");

        int num = printCartBuy();
        if(num == 1)
        {
            addCart("자가진단 키트", 10000);
        }
        else if (num == 2)
        {
            addCart("자가진단 키트", 10000);
            buy();
        }
    }
}