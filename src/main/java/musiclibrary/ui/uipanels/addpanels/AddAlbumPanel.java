package musiclibrary.ui.uipanels.addpanels;

import musiclibrary.ui.uiconstants.TextFieldConsts;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.LEADING;

public class AddAlbumPanel extends JPanel{
    MigLayout layout;
    JButton saveButton;
    JLabel nameLabel;
    JTextField inputNameTextField;

    public AddAlbumPanel() {
        this(null);
    }

    public AddAlbumPanel(String name) {
        setName(name);
        layout = new MigLayout("debug");
        setLayout(layout);
        saveButton = new JButton("Save", new ImageIcon("scr/img/save.png"));
        nameLabel = new JLabel("Album name");
        inputNameTextField = new JTextField(TextFieldConsts.WIDTH);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        add(nameLabel);
        add(inputNameTextField, "wrap");
        add(saveButton, "span");
    }
}
