package musiclibrary.ui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class TestFrame extends JFrame {
    public TestFrame() {
        super("Simple App");
        setLayout(new CardLayout());
        setSize(230, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JLabel label = new JLabel("Label:");
        JTextField textField = new JTextField();
        JCheckBox checkBox1 = new JCheckBox("CheckBox1");
        JCheckBox checkBox2 = new JCheckBox("CheckBox2");
        JCheckBox checkBox3 = new JCheckBox("CheckBox");
        JCheckBox checkBox4 = new JCheckBox("CheckBox4");
        JButton findButton = new JButton("Button 1");
        JButton cancelButton = new JButton("Button 2");

        checkBox1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBox2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBox3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkBox4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(textField)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(LEADING)
                                        .addComponent(checkBox1)
                                        .addComponent(checkBox3))
                                .addGroup(layout.createParallelGroup(LEADING)
                                        .addComponent(checkBox2)
                                        .addComponent(checkBox4))))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(findButton)
                        .addComponent(cancelButton))
        );

//        layout.linkSize(SwingConstants.HORIZONTAL, findButton, cancelButton);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(findButton))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(BASELINE)
                                        .addComponent(checkBox1)
                                        .addComponent(checkBox2))
                                .addGroup(layout.createParallelGroup(BASELINE)
                                        .addComponent(checkBox3)
                                        .addComponent(checkBox4)))
                        .addComponent(cancelButton))
        );

        pack();
//        show();
    }


}
