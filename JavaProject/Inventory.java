import java.util.HashMap;
import java.util.Map;

class Inventory
{
    private Map<String,Integer> invenNumber = new HashMap<String, Integer>()
    {{
        put("일회용 소형 마스크", 30);
        put("일회용 중형 마스크", 30);
        put("일회용 대형 마스크", 30);
        put("KF80 소형 마스크", 30);
        put("KF80 중형 마스크", 30);
        put("KF80 대형 마스크", 30);
        put("KF94 소형 마스크", 30);
        put("KF94 중형 마스크", 30);
        put("KF94 대형 마스크", 30);
        put("포켓형 손소독제", 10);
        put("살균형 손소독제", 10);
        put("핸드펌프 손소독제", 10);
        put("자가진단 키트", 10);
    }};

    // 재고 수 반환 메소드
    public int getInvNum(String name)
    {
        return invenNumber.get(name); //name key 에 해당하는 value 값 반환
    }

    // 재고 추가 메소드
    public void addInvNum(String name, int add)
    {

        int num = invenNumber.get(name);
        num += add;
        invenNumber.put(name, num);
    }

    // 재고 감소 메소드
    public void sellItems(String name, int sell)
    {
        invenNumber.put(name, invenNumber.get(name)-sell);
    }

    // 전체 재고 수 반환 메소드
    public String getInvens()
    {
        String invens = "";

        for (Map.Entry<String, Integer> entry : invenNumber.entrySet())
        {
            invens += entry.getKey() + " - " + entry.getValue() + "개 \n";
        }
        System.out.println();
        return invens;

    }
}