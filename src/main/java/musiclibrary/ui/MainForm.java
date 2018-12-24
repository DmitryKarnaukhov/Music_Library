package musiclibrary.ui;

import javax.swing.*;
import javax.swing.text.LayoutQueue;
import java.awt.*;

public class MainForm extends JFrame {
    private JPanel mainPane,
                   contentPanel,
                   chooseSourcePanel;
    private JLabel userHeadLabel,
                   trackListHeadLabel,
                   trackHeadLabel,
                   artistHeadLabel;

    public MainForm() {
        init();
    }

    private void init() {
        mainPane = (JPanel) this.getContentPane();
        mainPane.setLayout(new BorderLayout());
        this.setVisible(true);
        chooseSourcePanel = new JPanel(new BorderLayout());
        mainPane.add(chooseSourcePanel, BorderLayout.NORTH);
        JScrollPane contentScrollPanel = new JScrollPane();
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(contentScrollPanel, BorderLayout.NORTH);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        this.setSize(new Dimension(screenWidth / 2, screenHeight / 2));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Music Library");

        enableEvents(AWTEvent.WINDOW_EVENT_MASK);

        userHeadLabel = new JLabel("USER");
        trackListHeadLabel = new JLabel("TRACKLIST");
        trackHeadLabel = new JLabel("TRACK");
        artistHeadLabel = new JLabel("ARTIST");
        JPanel topHorizontalPanel = new JPanel();
        GridLayout entityGrid = new GridLayout(1, 4);
//        BoxLayout topHorizontalPanelLayout = new BoxLayout(topHorizontalPanel, BoxLayout.LINE_AXIS);
        topHorizontalPanel.setLayout(entityGrid);
        topHorizontalPanel.add(userHeadLabel);
        topHorizontalPanel.add(trackListHeadLabel);
        topHorizontalPanel.add(trackHeadLabel);
        topHorizontalPanel.add(artistHeadLabel);
        chooseSourcePanel.add(topHorizontalPanel);

        Font topMenuFont = userHeadLabel.getFont();
        topMenuFont = new Font(topMenuFont.getName(), Font.PLAIN, 50);
        Color topMenuColor = new Color(201, 42, 57);
        userHeadLabel.setFont(topMenuFont);
        userHeadLabel.setForeground(topMenuColor);
        trackListHeadLabel.setFont(topMenuFont);
        trackListHeadLabel.setForeground(topMenuColor);
        trackHeadLabel.setFont(topMenuFont);
        trackHeadLabel.setForeground(topMenuColor);
        artistHeadLabel.setFont(topMenuFont);
        artistHeadLabel.setForeground(topMenuColor);
    }
}
