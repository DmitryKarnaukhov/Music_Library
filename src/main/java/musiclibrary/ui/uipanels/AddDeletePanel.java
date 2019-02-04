package musiclibrary.ui.uipanels;

import musiclibrary.ui.listeners.mouselisteners.AddDeleteSearchMouseListener;

import static musiclibrary.ui.uiconstants.ActionNames.*;

import javax.swing.*;
import java.awt.*;

import static musiclibrary.ui.uiconstants.FontConstants.HEAD_LABEL_OUT_FONT_SIZE_K;

public class AddDeletePanel extends JPanel {
    public MainTableCardPanel mainTableCardPanel;
    private JLabel addLabel,
                   deleteLabel,
                   searchLabel;
    private FlowLayout layout;

    public AddDeletePanel(MainTableCardPanel mainTableCardPanel) {
        layout = new FlowLayout(FlowLayout.CENTER, 50, 10);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int fontSize = screenSize.height / HEAD_LABEL_OUT_FONT_SIZE_K;
        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, fontSize);
        this.mainTableCardPanel = mainTableCardPanel;
        AddDeleteSearchMouseListener addDeleteSearchMouseListener =
                new AddDeleteSearchMouseListener(mainTableCardPanel);
        addLabel = new JLabel(ACTION_NAME_ADD, new ImageIcon("src/img/plus.jpg"), SwingConstants.CENTER);
        addLabel.setFont(labelFont);
        addLabel.addMouseListener(addDeleteSearchMouseListener);
        deleteLabel = new JLabel(ACTION_NAME_DELETE, new ImageIcon("src/img/minus.jpg"), SwingConstants.CENTER);
        deleteLabel.setFont(labelFont);
        deleteLabel.addMouseListener(addDeleteSearchMouseListener);
        searchLabel = new JLabel(ACTION_NAME_SEARCH, new ImageIcon("src/img/glass.png"), SwingConstants.CENTER);
        searchLabel.setFont(labelFont);
        searchLabel.addMouseListener(addDeleteSearchMouseListener);
        add(addLabel);
        add(deleteLabel);
        add(searchLabel);
    }
}