package musiclibrary.ui.uipanels;

import musiclibrary.entities.Artist;
import musiclibrary.entities.Track;
import musiclibrary.entities.TrackList;
import musiclibrary.entities.User;
import musiclibrary.ui.tables.MainDataTable;
import musiclibrary.ui.tables.collnames.ReflectionCollNames;
import musiclibrary.ui.uiconstants.ActionNames;
import musiclibrary.ui.uipanels.addpanels.*;
import musiclibrary.ui.uipanels.searchpanels.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static musiclibrary.ui.uiconstants.ActionNames.*;
import static musiclibrary.ui.uiconstants.EntityNames.*;
import static musiclibrary.ui.uiconstants.EntityNames.ENTITY_NAME_ARTIST;

public class MainTablePanel extends JPanel{
    private MigLayout layout;
    private MainDataTable userTable,
            trackTable,
            trackListTable,
            artistTable;
    private JScrollPane userScroll,
            trackScroll,
            trackListScroll,
            artistScroll;
    private AddUserPanel addUserPanel;
    private AddArtistPanel addArtistPanel;
    private AddTrackPanel addTrackPanel;
    private AddTrackListPanel addTrackListPanel;
    private AddAlbumPanel addAlbumPanel;
    private SearchAlbumPanel searchAlbumPanel;
    private SearchArtistPanel searchArtistPanel;
    private SearchTrackListPanel searchTrackListPanel;
    private SearchTrackPanel searchTrackPanel;
    private SearchUserPanel searchUserPanel;
    public MainTablePanel() {
        layout = new MigLayout("alignx center, debug");
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
        userScroll.setName(ENTITY_NAME_USER);
        CellEditor cellEditor = new DefaultCellEditor(new JTextField());
        //userTable.getColumnModel().getColumn(0).setMaxWidth(200);
        //userTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        trackTable = new MainDataTable(trackTableData, trackCollNames);
        trackScroll = new JScrollPane(trackTable);
        trackScroll.setName(ENTITY_NAME_TRACK);
        trackListTable = new MainDataTable(trackListTableData, trackListCollNames);
        trackListScroll = new JScrollPane(trackListTable);
        trackListScroll.setName(ENTITY_NAME_TRACKLIST);
        artistTable = new MainDataTable(artistTableData, artistCollNames);
        artistScroll = new JScrollPane(artistTable);
        artistScroll.setName(ENTITY_NAME_ARTIST);
//        add(userScroll);
        addUserPanel = new AddUserPanel(ACTION_NAME_ADD_USER);
        addTrackPanel = new AddTrackPanel(ACTION_NAME_ADD_TRACK);
        addArtistPanel = new AddArtistPanel(ACTION_NAME_ADD_ARTIST);
        addTrackListPanel = new AddTrackListPanel(ACTION_NAME_SEARCH_TRACKLIST);
        addAlbumPanel = new AddAlbumPanel(ACTION_NAME_SEARCH_ARTIST);
        searchAlbumPanel = new SearchAlbumPanel(ACTION_NAME_SEARCH_ALBUM);
        searchArtistPanel = new SearchArtistPanel(ACTION_NAME_SEARCH_ARTIST);
        searchTrackListPanel = new SearchTrackListPanel(ACTION_NAME_SEARCH_TRACKLIST);
        searchTrackPanel = new SearchTrackPanel(ACTION_NAME_SEARCH_TRACK);
        searchUserPanel = new SearchUserPanel(ACTION_NAME_SEARCH_USER);

        AddTrackListPanel addTrackListPanel = new AddTrackListPanel();
        AddArtistPanel addArtistPanel = new AddArtistPanel();
        add(userScroll);

//        add(ENTITY_NAME_TRACK, trackScroll);
//        add(ENTITY_NAME_TRACKLIST, trackListScroll);
//        add(ENTITY_NAME_ARTIST, artistScroll);
    }

    public void addTable(String name) {
        switch (name) {
            case ENTITY_NAME_USER:
                removeAll();
                add(userScroll);
                revalidate();
                break;
            case ENTITY_NAME_TRACK:
                removeAll();
                add(trackScroll);
                revalidate();
                break;
            case ENTITY_NAME_TRACKLIST:
                removeAll();
                add(trackListScroll);
                revalidate();
                break;
            case ENTITY_NAME_ARTIST:
                removeAll();
                add(artistScroll);
                revalidate();
                break;
            case ACTION_NAME_ADD_ARTIST:
                removeAll();
                add(addArtistPanel);
                break;
            case ACTION_NAME_ADD_TRACK:
                removeAll();
                add(addTrackPanel);
                revalidate();
                break;
            case ACTION_NAME_ADD_TRACKLIST:
                removeAll();
                add(addTrackListPanel);
                revalidate();
                break;
            case ACTION_NAME_ADD_USER:
                removeAll();
                add(addUserPanel);
                revalidate();
                break;
            case ACTION_NAME_ADD_ALBUM:
                removeAll();
                add(addAlbumPanel);
                revalidate();
                break;
            case ACTION_NAME_SEARCH_ARTIST:
                removeAll();
                add(searchArtistPanel);
                revalidate();
                break;
            case ACTION_NAME_SEARCH_TRACKLIST:
                removeAll();
                add(searchTrackListPanel);
                revalidate();
                break;
            case ACTION_NAME_SEARCH_TRACK:
                removeAll();
                add(searchTrackPanel);
                revalidate();
                break;
            case ACTION_NAME_SEARCH_ALBUM:
                removeAll();
                add(searchAlbumPanel);
                revalidate();
                break;
            case ACTION_NAME_SEARCH_USER:
                removeAll();
                add(searchUserPanel);
                revalidate();
                break;
            case ACTION_NAME_ADD:
                Component component = getComponent(0);
                if (component == artistScroll || component == searchArtistPanel) {
                    removeAll();
                    add(addArtistPanel);
                    revalidate();
                } else if (component == trackListScroll || component == searchTrackListPanel) {
                    removeAll();
                    add(addTrackListPanel);
                    revalidate();
                } else if (component == trackScroll || component == searchTrackPanel) {
                    removeAll();
                    add(addTrackPanel);
                    revalidate();
                } else if (component == userScroll || component == searchUserPanel) {
                    removeAll();
                    add(addUserPanel);
                    revalidate();
                }
                break;
            case ACTION_NAME_SEARCH:
                Component component1 = getComponent(0);
                if (component1 == artistScroll || component1 == addArtistPanel) {
                    removeAll();
                    add(searchArtistPanel);
                    revalidate();
                } else if (component1 == trackListScroll || component1 == addTrackListPanel) {
                    removeAll();
                    add(searchTrackListPanel);
                    revalidate();
                } else if (component1 == trackScroll || component1 == addTrackPanel) {
                    removeAll();
                    add(searchTrackPanel);
                    revalidate();
                } else if (component1 == userScroll || component1 == addUserPanel) {
                    removeAll();
                    add(searchUserPanel);
                    revalidate();
                }
                break;
        }
    }

}
