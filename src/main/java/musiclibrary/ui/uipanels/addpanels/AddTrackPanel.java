package musiclibrary.ui.uipanels.addpanels;

import musiclibrary.entities.Genre;
import musiclibrary.ui.uiconstants.TextFieldConsts;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.LEADING;

public class AddTrackPanel extends JPanel {
    MigLayout layout;
    JButton saveButton,
            addArtistButton;
    JLabel nameLabel,
           artistLabel,
           trackLengthLabel,
           genreLabel;
    JTextField inputNameTextField,
               trackLengthTextField;
    JComboBox artistComboBox,
              genreComboBox;

    public AddTrackPanel() {
        this(null);
    }

    public AddTrackPanel(String name) {
        setName(name);
        layout = new MigLayout("debug");
        setLayout(layout);
        saveButton = new JButton("Save", new ImageIcon("scr/img/save.png"));
        addArtistButton = new JButton("Add artist", new ImageIcon("scr/img/plus.jpg"));
        nameLabel = new JLabel("Track name");
        inputNameTextField = new JTextField(TextFieldConsts.WIDTH);
        artistLabel = new JLabel("Artist");
        artistComboBox = new JComboBox();
        trackLengthLabel = new JLabel("");
        trackLengthTextField = new JTextField(TextFieldConsts.WIDTH);
        genreLabel = new JLabel("Genre");
        genreComboBox = new JComboBox(Genre.values());

        add(nameLabel);
        add(inputNameTextField, "wrap");
        add(artistLabel);
        add(artistComboBox, "alignx right");
        add(addArtistButton, "wrap");
        add(trackLengthLabel);
        add(trackLengthTextField, "wrap");
        add(genreLabel);
        add(genreComboBox, "wrap, alignx right");
        add(saveButton);
    }
}
