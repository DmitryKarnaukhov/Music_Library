package musiclibrary.ui.uipanels.searchpanels;

import musiclibrary.ui.uiconstants.TextFieldConsts;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SearchArtistPanel extends JPanel {
    MigLayout layout;
    JButton searchButton;
    JLabel nameLabel,
           idLabel;
    JTextField inputNameTextField,
               inputIdTextField;

    public SearchArtistPanel() {
        this(null);
    }

    public SearchArtistPanel(String name) {
        setName(name);
        layout = new MigLayout("debug");
        setLayout(layout);
        idLabel = new JLabel("Id");
        searchButton = new JButton("Search", new ImageIcon("scr/img/glass.png"));
        nameLabel = new JLabel("Artist name");
        inputNameTextField = new JTextField(TextFieldConsts.WIDTH);
        inputIdTextField = new JTextField(TextFieldConsts.WIDTH);

        add(idLabel);
        add(inputIdTextField, "wrap");
        add(nameLabel);
        add(inputNameTextField, "wrap");
        add(searchButton, "span");
    }
}
