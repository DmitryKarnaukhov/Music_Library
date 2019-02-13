package musiclibrary.ui.uipanels.searchpanels;

import musiclibrary.entities.Genre;
import musiclibrary.ui.uiconstants.TextFieldConsts;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SearchTrackPanel extends JPanel {
    MigLayout layout;
    JButton saveButton;
    JLabel nameLabel,
            artistLabel,
            trackLengthLabel,
            genreLabel,
            idLabel;
    JTextField inputNameTextField,
            trackLengthTextField,
            inputIdTextField;
    JComboBox artistComboBox,
            genreComboBox;

    public SearchTrackPanel() {
        this(null);
    }

    public SearchTrackPanel(String name) {
        setName(name);
        layout = new MigLayout("debug");
        setLayout(layout);
        saveButton = new JButton("Save", new ImageIcon("scr/img/save.png"));
        nameLabel = new JLabel("Track name");
        idLabel = new JLabel("Id");
        inputIdTextField = new JTextField(TextFieldConsts.WIDTH);
        inputNameTextField = new JTextField(TextFieldConsts.WIDTH);
        artistLabel = new JLabel("Artist");
        artistComboBox = new JComboBox();
        trackLengthLabel = new JLabel("");
        trackLengthTextField = new JTextField(TextFieldConsts.WIDTH);
        genreLabel = new JLabel("");
        genreComboBox = new JComboBox(Genre.values());

        add(idLabel);
        add(inputIdTextField, "wrap");
        add(nameLabel);
        add(inputNameTextField, "wrap");
        add(artistLabel);
        add(artistComboBox, "wrap, alignx right");
        add(trackLengthLabel);
        add(trackLengthTextField, "wrap");
        add(genreLabel);
        add(genreComboBox, "wrap, alignx right");
        add(saveButton);
    }
}
