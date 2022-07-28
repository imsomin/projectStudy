// 용지 재고 클래스
class Bill
{
    // 용지 재고 (%)
    private static int billNum = 100; //100%

    // 용지 재고 확인 메소드
    public static int getBill()
    {
        return billNum;
    }

    // 용지 출력 시, 용지의 재고가 감소하는 메소드
    public static void useBill()
    {
        billNum--;
    }

    // 용지 재고 채우기 메소드
    public static void addBill(int num)
    {
        billNum += num;
    }
}

