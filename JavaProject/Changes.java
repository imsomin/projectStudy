import java.util.HashMap;
import java.util.Map;

// 잔돈 클래스
class Changes
{
    private Map<Integer,Integer> changesNum = new HashMap<Integer, Integer>()
    {{
        put(5000, 4);
        put(1000, 20);
        put(500, 30);
        put(100, 50);
    }};

    // 잔돈 재고 확인 메소드
    public int getChanges(int unit)
    {
        return changesNum.get(unit);
    }


    // 잔돈 사용 메소드
    public String useChanges(int chan)
    {
        int zandon = chan;
        int ochun = chan/5000;
        int chun = chan%5000/1000;
        int obaek = chan%5000%1000/500;
        int baek = chan%5000%1000/500/100;

        if(changesNum.get(5000) < ochun) // 5000원짜리 부족
        {
            ochun = changesNum.get(5000); // 1장이라도 있으면 그거라도 잔돈에 포함 (아예 없으면 0)
            zandon -= 5000 * ochun;
            chun = zandon/1000;
            if(changesNum.get(1000) < chun)
            {
                chun = changesNum.get(1000);
                zandon -= 1000 * chun;
                obaek = zandon/500;
                if(changesNum.get(500) < obaek) // 500 부족
                {
                    obaek = changesNum.get(500);
                    zandon -= 500 * obaek;
                    baek = zandon/100;
                    if(changesNum.get(100) < baek) // 100 부족
                    {
                        return "잔돈이 부족합니다.";
                    }
                }
                else if(changesNum.get(100) < baek) // 100 부족
                {
                    return "잔돈이 부족합니다.";
                }
            }
            else if(changesNum.get(500) < obaek) // 5000 부족, 1000 충분, 500 부족
            {
                obaek = changesNum.get(500);
                zandon -= 500 * obaek;
                baek = zandon/100;
                if(changesNum.get(100) < baek) // 100 부족
                {
                    return "잔돈이 부족합니다.";
                }
            }
            else if(changesNum.get(100) < baek) // 5000 1000 500 충분, 100 부족
            {
                return "잔돈이 부족합니다.";
            }
        }
        else if(changesNum.get(1000) < chun)
        {
            chun = changesNum.get(1000);
            zandon -= 1000 * chun;
            obaek = zandon/500;
            if(changesNum.get(500) < obaek) // 500원짜리가 부족할 경우
            {
                obaek = changesNum.get(500);
                zandon -= 500 * obaek;
                baek = zandon/100;
                if(changesNum.get(100) < baek) // 100원짜리가 부족할 경우
                {
                    return "잔돈이 부족합니다.";
                }
            }
            else if(changesNum.get(100) < baek) // 100원짜리가 부족할 경우
            {
                return "잔돈이 부족합니다.";
            }
        }
        else if(changesNum.get(500) < obaek) // 5000원짜리 부족, 1000원짜리는 충분, 500원짜리 부족할 경우
        {
            obaek = changesNum.get(500);
            zandon -= 500 * obaek;
            baek = zandon/100;
            if(changesNum.get(100) < baek) // 100원짜리가 부족할 경우
            {
                return "잔돈이 부족합니다.";
            }
        }
        else if(changesNum.get(100) < baek) // 5000 1000 500 충분, 100 부족
        {
            return "잔돈이 부족합니다.";
        }

        // 사용한 잔돈 감소
        changesNum.put(5000, changesNum.get(5000)-ochun);
        changesNum.put(1000, changesNum.get(1000)-chun);
        changesNum.put(500, changesNum.get(500)-obaek);
        changesNum.put(100, changesNum.get(100)-obaek);

        return "5000원 : " + ochun + "개, " + "1000원 : " + chun + "개, " + "500원 : " + obaek + "개, " + "100원 : " + baek + "개";
    }

    // 잔돈 재고 채우기 메소드
    public void addChanges(int unit, int num)
    {
        changesNum.put(unit,changesNum.get(unit)+num);
    }

    // 잔돈 재고 회수 메소드
    public int subChanges(int unit, int num)
    {
        if(num > changesNum.get(unit))
        {
            System.out.printf("%d개가 부족합니다.\n", -(changesNum.get(unit)-num));
            return 0;
        }
        else
        {
            changesNum.put(unit,changesNum.get(unit)-num);
            System.out.println("제거가 완료되었습니다.");
            return 1;
        }
    }
}