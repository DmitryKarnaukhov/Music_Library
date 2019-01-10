package musiclibrary.ui;

import musiclibrary.ui.uipanels.HeadLabelPanel;
import musiclibrary.ui.uipanels.TableCardPanel;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class MainFormVer2 extends JFrame {
//    private GridLayout layout;
    private GroupLayout layout;
    private Dimension screenSize;
    private HeadLabelPanel headLabelPanel;
    private TableCardPanel tableCardPanel;

    public MainFormVer2() {
        init();
        tableCardPanel = new TableCardPanel();
        headLabelPanel = new HeadLabelPanel(tableCardPanel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(LEADING)
                                        .addComponent(headLabelPanel)
                                        .addComponent(tableCardPanel)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                        .addComponent(headLabelPanel)
                        .addComponent(tableCardPanel)
        );
    }

    private void init() {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        layout = new GridLayout(2,1);
        layout = new GroupLayout(getContentPane());
        setLayout(layout);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        this.setSize(new Dimension(screenWidth / 2, screenHeight / 2));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Music Library");
    }
}
