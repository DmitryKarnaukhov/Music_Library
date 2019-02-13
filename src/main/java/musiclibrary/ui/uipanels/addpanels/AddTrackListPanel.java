package musiclibrary.ui.uipanels.addpanels;

import musiclibrary.entities.Genre;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.LEADING;

public class AddTrackListPanel extends JPanel {
    MigLayout layout;
    JButton saveButton,
            addTrackButton;
    JLabel albumLabel,
           tracksLabel;
    JComboBox albumComboBox;
    JList tracksList;

    public AddTrackListPanel() {
        this(null);
    }

    public AddTrackListPanel(String name) {
        setName(name);
        layout = new MigLayout();
        setLayout(layout);
        saveButton = new JButton("Save", new ImageIcon("scr/img/save.png"));
        addTrackButton = new JButton("Add track", new ImageIcon("scr/img/plus.jpg"));
        albumLabel = new JLabel("Album");
        tracksLabel = new JLabel("Choose tracks");
        albumComboBox = new JComboBox();
        albumComboBox.addItem("Album#1");
        albumComboBox.addItem("Album#2");
        tracksList = new JList(new String[] { "TrackList#1", "TrackList#2" });

        add(albumLabel);
        add(albumComboBox, "wrap, alignx right");
        add(tracksLabel, "wrap");
        add(tracksList);
        add(addTrackButton, "wrap");
        add(saveButton);
    }
}
