

class PrintBill implements Print
{
    private int num = 1;

    @Override
    public void print()
    {}

    @Override
    public void print(int money, int changes)
    {
        Text te = new Text();

        String name = Top.ca.getCartName();
        String[] str = name.split(",");

        System.out.println("\n          [ 영수증 ]");
        System.out.println("-----------------------------------");
        System.out.println("      [    주문번호  :  " + num++ + "  ]");
        System.out.println("       품명                수량  ");
        System.out.println("-----------------------------------");
        for(String a : str)
        {
            System.out.println( a + "                " + Top.ca.getItemNum(a) );
        }
        System.out.println("-----------------------------------");
        System.out.println("주문합계  :               " + Top.ca.getPrice());
        System.out.println("받은 금액 :               " + money);
        System.out.println("잔     돈 :               " + changes);
        System.out.println("-----------------------------------");
        System.out.println(Top.ch.useChanges(changes));
        System.out.println("\n구매해주셔서 감사합니다.");
        te.print();

        Top.billgee.useBill();
    }
}