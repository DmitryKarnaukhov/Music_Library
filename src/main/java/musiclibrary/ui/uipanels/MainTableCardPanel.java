package musiclibrary.ui.uipanels;

import musiclibrary.entities.Artist;
import musiclibrary.entities.Track;
import musiclibrary.entities.TrackList;
import musiclibrary.entities.User;
import musiclibrary.ui.tables.MainDataTable;
import musiclibrary.ui.tables.collnames.ReflectionCollNames;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import static musiclibrary.ui.uiconstants.EntityNames.*;

public class MainTableCardPanel extends JPanel {
    private CardLayout layout;
    private MainDataTable userTable,
                          trackTable,
                          trackListTable,
                          artistTable;
    private JScrollPane userScroll,
                        trackScroll,
                        trackListScroll,
                        artistScroll;
    public MainTableCardPanel() {
        layout = new CardLayout(50, 0);
        setLayout(layout);

        String[] userCollNames = new String[0];
        userCollNames = ReflectionCollNames.getEntityFieldNames(User.class);
        String[][] userTableData = new String[][] {
                { "1", "Henry 1st", "1trackList" },
                { "2", "Monroe", "2trackList" }
        };
        String[] trackCollNames = ReflectionCollNames.getEntityFieldNames(Track.class);
        String[][] trackTableData = new String[][] {
                { "1", "myName", "artist1", "3.0", "Rock" },
                { "1", "myName", "artist1", "3.0", "Rock" }
        };
        String[] trackListCollNames = ReflectionCollNames.getEntityFieldNames(TrackList.class);
        String[][] trackListTableData = new String[][] {
                { "1", "Album#1", "First track" },
                { "2", "Album#2", "First track" }
        };
        String[] artistCollNames = ReflectionCollNames.getEntityFieldNames(Artist.class);
        String[][] artistTableData = new String[][] {
                { "1", "Star#1" },
                { "2", "Star#2" },
        };
        userTable = new MainDataTable(userTableData, userCollNames);
        userScroll = new JScrollPane(userTable);

        CellEditor cellEditor = new DefaultCellEditor(new JTextField());
        //userTable.getColumnModel().getColumn(0).setMaxWidth(200);
        //userTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        trackTable = new MainDataTable(trackTableData, trackCollNames);
        trackScroll = new JScrollPane(trackTable);
        trackListTable = new MainDataTable(trackListTableData, trackListCollNames);
        trackListScroll = new JScrollPane(trackListTable);
        artistTable = new MainDataTable(artistTableData, artistCollNames);
        artistScroll = new JScrollPane(artistTable);
        add(ENTITY_NAME_USER, userScroll);
        add(ENTITY_NAME_TRACK, trackScroll);
        add(ENTITY_NAME_TRACKLIST, trackListScroll);
        add(ENTITY_NAME_ARTIST, artistScroll);
    }
}
