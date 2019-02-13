package musiclibrary.ui.uipanels;

import musiclibrary.ui.listeners.mouselisteners.HeadLabelMouseListener;
import net.miginfocom.swing.MigLayout;

import static musiclibrary.ui.uiconstants.EntityNames.*;

import javax.swing.*;
import java.awt.*;

import static musiclibrary.ui.uiconstants.FontConstants.HEAD_LABEL_OUT_FONT_SIZE_K;

public class HeadLabelPanel extends JPanel {
    public MainTablePanel mainTablePanel;
    private JLabel userHeadLabel,
                   trackListHeadLabel,
                   trackHeadLabel,
                   artistHeadLabel;
    private MigLayout layout;
    public HeadLabelPanel(MainTablePanel mainTablePanel) {
        this.mainTablePanel = mainTablePanel;
        layout = new MigLayout("debug");
        setLayout(layout);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int fontSize = screenSize.height / HEAD_LABEL_OUT_FONT_SIZE_K;
        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, fontSize);
        HeadLabelMouseListener headLabelMouseListener = new HeadLabelMouseListener(mainTablePanel);
        userHeadLabel = new JLabel(ENTITY_NAME_USER, new ImageIcon("src/img/user.jpg"), SwingConstants.CENTER);
        userHeadLabel.setFont(labelFont);
        userHeadLabel.addMouseListener(headLabelMouseListener);
        trackListHeadLabel = new JLabel(ENTITY_NAME_TRACKLIST, new ImageIcon("src/img/tracklist.png"), SwingConstants.CENTER);
        trackListHeadLabel.setFont(labelFont);
        trackListHeadLabel.addMouseListener(headLabelMouseListener);
        trackHeadLabel = new JLabel(ENTITY_NAME_TRACK, new ImageIcon("src/img/track.jpg"), SwingConstants.CENTER);
        trackHeadLabel.setFont(labelFont);
        trackHeadLabel.addMouseListener(headLabelMouseListener);
        artistHeadLabel = new JLabel(ENTITY_NAME_ARTIST, new ImageIcon("src/img/artist.png"), SwingConstants.CENTER);
        artistHeadLabel.setFont(labelFont);
        artistHeadLabel.addMouseListener(headLabelMouseListener);
        add(userHeadLabel);
        add(trackListHeadLabel);
        add(trackHeadLabel);
        add(artistHeadLabel);
    }
}
