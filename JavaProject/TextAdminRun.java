import java.io.IOException;

class TextAdminRun extends TextAdmin {
    void textAdminRun()
    {
        do
        {
            menuDisp();
            try {
                menuSelect();
                menuRun();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (true);
    }
}
