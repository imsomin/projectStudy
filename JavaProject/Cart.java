import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.Iterator;
import java.util.Set;
import java.util.Scanner;

// 장바구니 클래스
class Cart
{
    // 장바구니에 담긴 각 물품의 가격
    private static Vector<Integer> price = new Vector<Integer>();

    // 장바구니에 담긴 물품과 그 개수 key-value값
    private static Map<String, Integer> nameCount = new HashMap<String, Integer>();

    // 금액 추가 메소드
    public void addPrice(int num)
    {
        price.add(num);
    }

    // 장바구니에 담긴 특정 물품의 개수 반환 메소드
    public int getItemNum(String name)
    {
        return nameCount.get(name);
    }

    // 장바구니에 담긴 물품의 총 금액 반환 메소드
    public int getPrice()
    {
        int total = 0;
        for(Integer a : price)
        {
            total += a;
        }
        return total;
    }

    // 장바구니에 담긴 물건의 이름과 개수 반환 메소드
    public String getCartItems()
    {
        Set key = nameCount.keySet();
        Iterator it = key.iterator();
        String items = "";
        if(it.hasNext())
        {
            for (Map.Entry<String, Integer> entry : nameCount.entrySet())
            {
                items += entry.getKey() + " - " + entry.getValue() + "개 ";
            }
            return items;
        }
        else
        {
            return "-1";
        }

    }

    // 장바구니에 담긴 물건의 이름 반환 메소드
    public String getCartName()
    {

        String cartName = "";
        for (Map.Entry<String, Integer> entry : nameCount.entrySet())
        {
            cartName += entry.getKey() + ",";
        }
        return cartName;
    }


    // 장바구니에 물건 추가 (나중에 User에 옮길 것?)
    public void addItems(String name, int n)
    {
        if(nameCount.containsKey(name))
        {
            nameCount.put(name, nameCount.get(name)+n);
        }
        else
        {
            nameCount.put(name, n);
        }
    }
    //장바구니에 물건 삭제
    public void deleteItems(String name,int n)
    {

        if (nameCount.containsKey(name))
        {
            nameCount.put(name,nameCount.get(name)-n);
            System.out.println("삭제되었습니다.");


            if (nameCount.get(name)==0)
            {
                nameCount.remove(name);
            }
        }

        String ch = getCartItems();
        System.out.print(ch);
    }

    // 장바구니 비우기
    public void deleteAll()
    {
        nameCount.clear();
    }

    // 장바구니에 담긴 물품 총 금액 비우기
    public void deletePrice()
    {
        price.clear();
    }

    // 장바구니 확인 메소드
    public void seeCart()
    {
        Scanner sc = new Scanner(System.in);
        int num = 0;

        if(getCartItems().equals("-1"))
        {
            System.out.println("현재 장바구니에 물건이 없습니다.");
        }
        else
        {
            System.out.println("\n현재 장바구니에 든 물품은 \"" + getCartItems() + "\" 입니다.\n");
        }

        do
        {
            System.out.println("\n[메뉴 선택]");
            System.out.println("[1] 장바구니 비우기 ");
            System.out.println("[2] 메뉴로 돌아가기 ");
            num = sc.nextInt();
        }
        while (num != 1 && num != 2);

        if(num == 1)
        {
            deleteAll();
            System.out.println("장바구니를 비웠습니다.");
        }
    }
}