package musiclibrary.ui.uipanels.searchpanels;

import musiclibrary.ui.uiconstants.TextFieldConsts;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SearchUserPanel extends JPanel {
    MigLayout layout;
    JButton searchButton;
    JLabel nameLabel,
            trackListName,
            idLabel;
    JTextField inputNameTextField,
               inputIdTextField;
    JComboBox albumComboBox;

    public SearchUserPanel() {
        this(null);
    }

    public SearchUserPanel(String name) {
        setName(name);
        layout = new MigLayout("debug");
        setLayout(layout);
        searchButton = new JButton("Save", new ImageIcon("scr/img/save.png"));
        nameLabel = new JLabel("User name");
        trackListName = new JLabel("TrackList name");
        inputNameTextField = new JTextField(TextFieldConsts.WIDTH);
        idLabel = new JLabel("Id");
        inputIdTextField = new JTextField(TextFieldConsts.WIDTH);
        albumComboBox = new JComboBox();
        albumComboBox.addItem("Album#1");
        albumComboBox.addItem("Album#2");

        add(idLabel);
        add(inputIdTextField, "wrap");
        add(nameLabel);
        add(inputNameTextField, "wrap");
        add(trackListName, "wrap");
        add(albumComboBox, "wrap, alignx right");
        add(searchButton);
    }
}
