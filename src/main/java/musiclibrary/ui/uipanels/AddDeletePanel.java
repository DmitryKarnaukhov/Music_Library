package musiclibrary.ui.uipanels;

import musiclibrary.ui.listeners.mouselisteners.AddDeleteSearchMouseListener;
import musiclibrary.ui.listeners.mouselisteners.HeadLabelMouseListener;
import musiclibrary.ui.listeners.mouselisteners.MainTableListener;
import net.miginfocom.swing.MigLayout;

import static musiclibrary.ui.uiconstants.ActionNames.*;

import javax.swing.*;
import java.awt.*;

import static musiclibrary.ui.uiconstants.FontConstants.HEAD_LABEL_OUT_FONT_SIZE_K;

public class AddDeletePanel extends JPanel {
    private MainTablePanel mainTablePanel;
    private JLabel addLabel,
                   deleteLabel,
                   searchLabel;
    private MigLayout layout;

    public AddDeletePanel(MainTablePanel mainTablePanel) {//, ResultCardPanel resultCardPanel) {
        layout = new MigLayout("debug, alignx center");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int fontSize = screenSize.height / HEAD_LABEL_OUT_FONT_SIZE_K;
        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, fontSize);
        this.mainTablePanel = mainTablePanel;
        HeadLabelMouseListener headLabelMouseListener =
                new HeadLabelMouseListener(mainTablePanel);
//        AddDeleteSearchMouseListener addDeleteSearchMouseListener =
//                new AddDeleteSearchMouseListener(mainTablePanel, resultCardPanel);
        addLabel = new JLabel(ACTION_NAME_ADD, new ImageIcon("src/img/plus.jpg"), SwingConstants.CENTER);
        addLabel.setFont(labelFont);
//        addLabel.addMouseListener(addDeleteSearchMouseListener);
        addLabel.addMouseListener(headLabelMouseListener);
        deleteLabel = new JLabel(ACTION_NAME_DELETE, new ImageIcon("src/img/minus.jpg"), SwingConstants.CENTER);
        deleteLabel.setFont(labelFont);
//        deleteLabel.addMouseListener(addDeleteSearchMouseListener);
        deleteLabel.addMouseListener(headLabelMouseListener);
        searchLabel = new JLabel(ACTION_NAME_SEARCH, new ImageIcon("src/img/glass.png"), SwingConstants.CENTER);
        searchLabel.setFont(labelFont);
//        searchLabel.addMouseListener(addDeleteSearchMouseListener);
        searchLabel.addMouseListener(headLabelMouseListener);
        add(addLabel);
        add(deleteLabel);
        add(searchLabel);
    }
}