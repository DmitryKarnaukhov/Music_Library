package musiclibrary.ui.uipanels;

import musiclibrary.ui.listeners.mouselisteners.HeadLabelMouseListener;
import static musiclibrary.ui.uiconstants.EntityNames.*;

import javax.swing.*;
import java.awt.*;

import static musiclibrary.ui.uiconstants.FontConstants.HEAD_LABEL_OUT_FONT_SIZE_K;

public class HeadLabelPanel extends JPanel {
    public TableCardPanel tableCardPanel;
    private JLabel userHeadLabel,
                   trackListHeadLabel,
                   trackHeadLabel,
                   artistHeadLabel;
    private FlowLayout layout;
    public HeadLabelPanel(TableCardPanel tableCardPanel) {
        this.tableCardPanel = tableCardPanel;
        layout = new FlowLayout(FlowLayout.CENTER, 50, 10);
        setLayout(layout);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int fontSize = screenSize.height / HEAD_LABEL_OUT_FONT_SIZE_K;
        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, fontSize);
        HeadLabelMouseListener headLabelMouseListener = new HeadLabelMouseListener(tableCardPanel);
        userHeadLabel = new JLabel(ENTITY_NAME_USER, SwingConstants.CENTER);
        userHeadLabel.setFont(labelFont);
        userHeadLabel.addMouseListener(headLabelMouseListener);
        trackListHeadLabel = new JLabel(ENTITY_NAME_TRACKLIST, SwingConstants.CENTER);
        trackListHeadLabel.setFont(labelFont);
        trackListHeadLabel.addMouseListener(headLabelMouseListener);
        trackHeadLabel = new JLabel(ENTITY_NAME_TRACK, SwingConstants.CENTER);
        trackHeadLabel.setFont(labelFont);
        trackHeadLabel.addMouseListener(headLabelMouseListener);
        artistHeadLabel = new JLabel(ENTITY_NAME_ARTIST, SwingConstants.CENTER);
        artistHeadLabel.setFont(labelFont);
        artistHeadLabel.addMouseListener(headLabelMouseListener);
        add(userHeadLabel);
        add(trackListHeadLabel);
        add(trackHeadLabel);
        add(artistHeadLabel);
    }
}
