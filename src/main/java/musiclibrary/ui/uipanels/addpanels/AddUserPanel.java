package musiclibrary.ui.uipanels.addpanels;

import musiclibrary.ui.uiconstants.TextFieldConsts;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.LEADING;

public class AddUserPanel extends JPanel {
    MigLayout layout;
    JButton saveButton,
            addTrackListButton;
    JLabel nameLabel,
           trackListName;
    JTextField inputNameTextField;
    JComboBox albumComboBox;

    public AddUserPanel() {
        this(null);
    }

    public AddUserPanel(String name) {
        setName(name);
        layout = new MigLayout("debug");
        setLayout(layout);
        saveButton = new JButton("Save", new ImageIcon("scr/img/save.png"));
        nameLabel = new JLabel("User name");
        trackListName = new JLabel("TrackList name");
        inputNameTextField = new JTextField(TextFieldConsts.WIDTH);
        addTrackListButton = new JButton("Add trackList", new ImageIcon("scr/img/plus.jpg"));
        albumComboBox = new JComboBox();
        albumComboBox.addItem("Album#1");
        albumComboBox.addItem("Album#2");
        add(nameLabel);
        add(inputNameTextField, "wrap");
        add(trackListName, "wrap");
        add(albumComboBox, "alignx right");
        add(addTrackListButton, "wrap");
        add(saveButton);
    }
}
