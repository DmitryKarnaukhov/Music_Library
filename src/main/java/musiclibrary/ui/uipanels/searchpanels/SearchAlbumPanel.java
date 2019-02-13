package musiclibrary.ui.uipanels.searchpanels;

import musiclibrary.ui.uiconstants.TextFieldConsts;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SearchAlbumPanel extends JPanel {
    MigLayout layout;
    JButton searchButton;
    JLabel nameLabel,
           idLabel;
    JTextField inputNameTextField,
               inputIdTextField;

    public SearchAlbumPanel() {
        this(null);
    }

    public SearchAlbumPanel(String name) {
        layout = new MigLayout("debug");
        setLayout(layout);
        searchButton = new JButton("Search", new ImageIcon("scr/img/glass.png"));
        nameLabel = new JLabel("Album name");
        idLabel = new JLabel("Id");
        inputNameTextField = new JTextField(TextFieldConsts.WIDTH);
        inputIdTextField = new JTextField(TextFieldConsts.WIDTH);

        add(idLabel);
        add(inputNameTextField, "wrap");
        add(nameLabel);
        add(inputNameTextField, "wrap");
        add(searchButton, "span");
    }
}
