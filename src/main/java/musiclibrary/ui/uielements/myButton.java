package musiclibrary.ui.uielements;

import javax.swing.*;
import java.awt.*;

public class myButton extends JButton {
    public myButton(String text) {
        super(text);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height / 20;
        int width = screenSize.width / 20;
        this.setSize(width, height);
    }

    public myButton(String text, Icon icon) {
        super(text, icon);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height / 30;
        int width = screenSize.width / 20;
        this.setPreferredSize(new Dimension(width, height));
        this.setIcon(icon);
    }
}
