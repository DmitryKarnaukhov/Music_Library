package musiclibrary.ui.uipanels.addpanels;

import musiclibrary.ui.uiconstants.TextFieldConsts;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.LEADING;

public class AddArtistPanel extends JPanel {
    MigLayout layout;
    JButton saveButton;
    JLabel nameLabel;
    JTextField inputNameTextField;

    public AddArtistPanel() {
        this(null);
    }

    public AddArtistPanel(String name) {
        setName(name);
        layout = new MigLayout();
        setLayout(layout);
        saveButton = new JButton("Save", new ImageIcon("scr/img/save.png"));
        nameLabel = new JLabel("Artist name");
        inputNameTextField = new JTextField(TextFieldConsts.WIDTH);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputNameTextField.getText() != "") {

                }
            }
        });

        add(nameLabel);
        add(inputNameTextField, "wrap");
        add(saveButton, "span");
    }
}
