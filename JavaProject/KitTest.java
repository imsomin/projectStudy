import java.io.IOException;

public class KitTest {

    public void KitRun() throws IOException
    {
        CheckKit ck = new CheckKit();

        ck.dateOfBirth();
        ck.checkSymptoms();
        ck.ageCalc();
    }
}
