package musiclibrary.ui;

import javax.swing.*;

public class TestForm {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new MainFormVer2();
//                new MainForm();
                new MainFormVer3();
            }
        });
    }
}
