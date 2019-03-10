package musiclibrary.ui;

import musiclibrary.entities.Track;
import musiclibrary.ui.listeners.mouselisteners.AddDeleteSearchMouseListener;
import musiclibrary.ui.listeners.mouselisteners.HeadLabelMouseListener;
import musiclibrary.ui.tables.ShowResultTable;
import musiclibrary.ui.tables.collnames.ReflectionCollNames;
import musiclibrary.ui.uipanels.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import static musiclibrary.ui.uiconstants.ActionNames.ACTION_NAME_ADD;
import static musiclibrary.ui.uiconstants.ActionNames.ACTION_NAME_DELETE;
import static musiclibrary.ui.uiconstants.ActionNames.ACTION_NAME_SEARCH;
import static musiclibrary.ui.uiconstants.EntityNames.*;
import static musiclibrary.ui.uiconstants.FontConstants.HEAD_LABEL_OUT_FONT_SIZE_K;

public class MainFormVer3 extends JFrame {
    private MigLayout layout;
    private Dimension screenSize;
    private HeadLabelPanel headLabelPanel;
    private AddDeletePanel addDeletePanel;
    private MainTablePanel mainTablePanel;
    private ResultCardPanel resultCardPanel;

    private String debugOn = "debug";
    JLabel idLabel = new JLabel("Id: "),
            resultIdLabel = new JLabel("1"),
            nameLabel = new JLabel("Name: "),
            resultNameLabel = new JLabel("1");
    private JLabel userHeadLabel,
            trackListHeadLabel,
            trackHeadLabel,
            artistHeadLabel;
    private JLabel addLabel,
            deleteLabel,
            searchLabel;
    String[] collNames = ReflectionCollNames.getEntityFieldNames(Track.class);
    JTable resultTable = new JTable(new Object[][] { { "1", "2", "1", "2", "1" }, { "1", "2", "1", "2", "1" } }, collNames);
    JPanel panel = new JPanel();

    public MainFormVer3() {
        init();
        mainTablePanel = new MainTablePanel(); // refactor
        headLabelPanel = new HeadLabelPanel(mainTablePanel);
        addDeletePanel = new AddDeletePanel(mainTablePanel);
        add(headLabelPanel, "alignx center, wrap");
        add(addDeletePanel, "alignx center, wrap");
        add(mainTablePanel, "align center");
    }

    private void init() {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        layout = new MigLayout("debug, alignx center");
        setLayout(layout);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        this.setSize(new Dimension(screenWidth / 2, screenHeight / 2));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Music Library");
    }

    private void initHeader() {
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
    }

    private void initAddDeleteSearchHeader() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int fontSize = screenSize.height / HEAD_LABEL_OUT_FONT_SIZE_K;
        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, fontSize);
        this.mainTablePanel = mainTablePanel;
        AddDeleteSearchMouseListener addDeleteSearchMouseListener =
                new AddDeleteSearchMouseListener(mainTablePanel, resultCardPanel);
        addLabel = new JLabel(ACTION_NAME_ADD, new ImageIcon("src/img/plus.jpg"), SwingConstants.CENTER);
        addLabel.setFont(labelFont);
        addLabel.addMouseListener(addDeleteSearchMouseListener);
        deleteLabel = new JLabel(ACTION_NAME_DELETE, new ImageIcon("src/img/minus.jpg"), SwingConstants.CENTER);
        deleteLabel.setFont(labelFont);
        deleteLabel.addMouseListener(addDeleteSearchMouseListener);
        searchLabel = new JLabel(ACTION_NAME_SEARCH, new ImageIcon("src/img/glass.png"), SwingConstants.CENTER);
        searchLabel.setFont(labelFont);
        searchLabel.addMouseListener(addDeleteSearchMouseListener);
    }
}
