package musiclibrary.ui.uipanels.addpanels;

import musiclibrary.entities.Artist;
import musiclibrary.ui.uiconstants.TextFieldConsts;
import musiclibrary.ui.uielements.myButton;
import musiclibrary.ui.uipanels.MainTablePanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddArtistPanel extends JPanel {
    private MainTablePanel mainTablePanel;
    MigLayout layout;
    JButton saveButton;
    JLabel nameLabel;
    JTextField inputNameTextField;

    public AddArtistPanel(MainTablePanel mainTablePanel) {
        this(null, mainTablePanel);
    }

    public AddArtistPanel(String name, MainTablePanel mainTablePanel) {
        this.mainTablePanel = mainTablePanel;
        setName(name);
        layout = new MigLayout();
        setLayout(layout);
//        saveButton = new JButton("Save", new ImageIcon("scr/img/save.png"));
        saveButton = new myButton("Save", new ImageIcon("src/img/save.png"));
        nameLabel = new JLabel("Artist name");
        inputNameTextField = new JTextField(TextFieldConsts.WIDTH);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputNameTextField.getText() != "" &&
                        inputNameTextField.getText() != null) {
                    mainTablePanel.artistView.add(new Artist(-1, inputNameTextField.getText()));
                    inputNameTextField.setText("");
                    mainTablePanel.refreshArtistTable();
                    JOptionPane.showMessageDialog(mainTablePanel, "Artist has been successfully saved!");
                }
            }
        });

        add(nameLabel);
        add(inputNameTextField, "wrap");
        add(saveButton, "span");
    }
}
