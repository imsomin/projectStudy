import java.util.HashMap;

public class Text implements Print
{
    TextAdmin x = new TextAdmin();

    private int returnRandomPhraseSize()
    {
        HashMap<Integer, String> phrase = x.getHm();

        int num = (int) (Math.random() * phrase.size()) + 1;

        return num;
    }


    @Override
    public void print()
    {
        HashMap<Integer, String> phrase = x.getHm();

        int num = returnRandomPhraseSize();

        System.out.println(phrase.get(num));
    }

    @Override
    public void print(int money, int changes) {

    }

}
