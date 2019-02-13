package musiclibrary.ui;

import musiclibrary.entities.Track;
import musiclibrary.ui.tables.ShowResultTable;
import musiclibrary.ui.tables.collnames.ReflectionCollNames;
import musiclibrary.ui.uipanels.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class MainFormVer2 extends JFrame {
//    private GroupLayout layout;
    private MigLayout layout;
    private Dimension screenSize;
    private HeadLabelPanel headLabelPanel;
    private AddDeletePanel addDeletePanel;
    private MainTableCardPanel mainTableCardPanel;
    private ResultCardPanel resultCardPanel;

    JLabel idLabel = new JLabel("Id: "),
            resultIdLabel = new JLabel("1"),
            nameLabel = new JLabel("Name: "),
            resultNameLabel = new JLabel("1");
    String[] collNames = ReflectionCollNames.getEntityFieldNames(Track.class);
    JTable resultTable = new JTable(new Object[][] { { "1", "2", "1", "2", "1" }, { "1", "2", "1", "2", "1" } }, collNames);
    JPanel panel = new JPanel();

    public MainFormVer2() {
        init();
        mainTableCardPanel = new MainTableCardPanel();
        resultCardPanel = new ResultCardPanel();
//        headLabelPanel = new HeadLabelPanel(mainTableCardPanel);
//        addDeletePanel = new AddDeletePanel(mainTableCardPanel, resultCardPanel);
        panel.add(resultTable);
//        layout.setAutoCreateGaps(true);
//        layout.setAutoCreateContainerGaps(true);
//
//        layout.setHorizontalGroup(layout.createParallelGroup(LEADING)
//                                        .addComponent(headLabelPanel)
//                                        .addComponent(addDeletePanel)
//                                        .addGroup(layout.createSequentialGroup()
//                                                .addComponent(mainTableCardPanel)
//                                           .addComponent(resultCardPanel)
//                                        )
//
//        );
//
        add(headLabelPanel, "span");
        add(addDeletePanel, "wrap");
        add(mainTableCardPanel);
        add(resultCardPanel);
//        layout.setVerticalGroup(layout.createSequentialGroup()
//                        .addComponent(headLabelPanel)
//                        .addComponent(addDeletePanel)
//                        .addGroup(layout.createParallelGroup()
//                                .addComponent(mainTableCardPanel)
//                            .addComponent(resultCardPanel)
//                        )
//        );
    }

    private void init() {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        layout = new GroupLayout(getContentPane());
        layout = new MigLayout();
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
