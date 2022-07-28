// 판매 수익 클래스
class Revenue
{
    private static int rev = 0;

    // 수익 추가 메소드
    public static void addMoney(int m)
    {
        rev += m;
    }

    // 수익 확인 메소드
    public static int getMoney()
    {
        return rev;
    }

    // 수익 회수 메소드
    public static int removeMoney(int m)
    {
        if(rev >= m)
        {
            rev -= m;
            return 1;
        }
        else
        {
            return -1;
        }
    }
}