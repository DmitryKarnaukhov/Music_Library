package musiclibrary.ui.uipanels.searchpanels;

import musiclibrary.ui.uiconstants.TextFieldConsts;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SearchTrackListPanel extends JPanel {
    MigLayout layout;
    JButton searchButton;
    JLabel albumLabel,
            tracksLabel,
            idLabel;
    JComboBox albumComboBox;
    JList tracksList;
    JTextField inputIdTextField;

    public SearchTrackListPanel() {
        this(null);
    }

    public SearchTrackListPanel(String name) {
        setName(name);
        layout = new MigLayout();
        setLayout(layout);
        idLabel = new JLabel("Id");
        inputIdTextField = new JTextField(TextFieldConsts.WIDTH);
        searchButton = new JButton("Search", new ImageIcon("scr/img/glass.png"));
        albumLabel = new JLabel("Album");
        tracksLabel = new JLabel("Choose tracks");
        albumComboBox = new JComboBox();
        albumComboBox.addItem("Album#1");
        albumComboBox.addItem("Album#2");
        tracksList = new JList(new String[] { "TrackList#1", "TrackList#2" });

        add(idLabel);
        add(inputIdTextField, "wrap");
        add(albumLabel);
        add(albumComboBox, "wrap, alignx right");
        add(tracksLabel, "wrap");
        add(tracksList, "wrap");
        add(searchButton);
    }
}
