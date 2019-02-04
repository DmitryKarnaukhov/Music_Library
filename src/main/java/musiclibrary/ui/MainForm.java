package musiclibrary.ui;

import musiclibrary.ui.tables.ShowResultTable;
import musiclibrary.ui.uipanels.AddDeletePanel;
import musiclibrary.ui.uipanels.HeadLabelPanel;
import musiclibrary.ui.uipanels.MainTableCardPanel;
import musiclibrary.ui.uipanels.ResultPanel;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class MainForm extends JFrame {
    //    private GridLayout layout;
    private GroupLayout layout;
    private Dimension screenSize;
    //private HeadLabelPanel headLabelPanel;
    //private AddDeletePanel addDeletePanel;
    //private MainTableCardPanel mainTableCardPanel;
//    private ResultPanel resultPanel;

    public MainForm() {
        init();
//        mainTableCardPanel = new MainTableCardPanel();
//        headLabelPanel = new HeadLabelPanel(mainTableCardPanel);
//        addDeletePanel = new AddDeletePanel(mainTableCardPanel);
//        resultPanel = new ResultPanel();
        JLabel l1 = new JLabel("Label 1");
        JLabel l2 = new JLabel("Label 2");
        JLabel l3 = new JLabel("Label 3");
        JLabel l4 = new JLabel("Label 4");
        JLabel l5 = new JLabel("Label 5");
        JLabel l6 = new JLabel("Label 6");
        JLabel l7 = new JLabel("Label 7");
        JLabel l8 = new JLabel("Label 8");
        JLabel l9 = new JLabel("Label 9");
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(l1)
                        .addComponent(l2)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(l3)
                        .addComponent(l4)
                )
                .addComponent(l5)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(l1)
                        .addComponent(l2)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(l3)
                        .addComponent(l4)
                )
                .addComponent(l5)
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
