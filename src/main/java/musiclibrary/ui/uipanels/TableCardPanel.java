package musiclibrary.ui.uipanels;

import javax.swing.*;
import java.awt.*;
import static musiclibrary.ui.uiconstants.EntityNames.*;

public class TableCardPanel extends JPanel {
    private CardLayout layout;
    private JTable userTable,
                   trackTable,
                   trackListTable,
                   artistTable;
    private JScrollPane userScroll,
                        trackScroll,
                        trackListScroll,
                        artistScroll;
    public TableCardPanel() {
        layout = new CardLayout(50, 0);
        setLayout(layout);
        String[] userCollNames = new String[] { "id", "name", "trackLists" };
        String[][] userTableData = new String[][] {
                { "1", "Henry 1st", "1trackList" },
                { "2", "Monroe", "2trackList" }
        };
        String[] trackCollNames = new String[] { "id", "name", "artist", "trackLenght", "genre" };
        String[][] trackTableData = new String[][] {
                { "1", "myName", "artist1", "3.0", "Rock" },
                { "1", "myName", "artist1", "3.0", "Rock" }
        };
        userTable = new JTable(userTableData, userCollNames);
        userScroll = new JScrollPane(userTable);
        trackTable = new JTable(trackTableData, trackCollNames);
        trackScroll = new JScrollPane(trackTable);
        add(ENTITY_NAME_USER, userScroll);
        add(ENTITY_NAME_TRACK, trackScroll);
    }
}
